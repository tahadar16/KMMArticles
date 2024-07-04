//
//  ArticlesScreen.swift
//  iosApp
//
//  Created by Taha Dar on 16/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticlesScreen {
        
    @MainActor
    class ArticlesViewModelWrapper: ObservableObject {
        let articlesViewModel: ArticleViewModel
        
        
        init() {
            articlesViewModel = ArticlesInjector().articleViewModel
            articlesState = articlesViewModel.articlesState.value
        }
        
        @Published var articlesState: ArticleState
        
        func startObserving() {
            Task {
                for await articlesS in articlesViewModel.articlesState {
                    self.articlesState = articlesS
                }
            }
        }
    }
}

struct ArticlesScreen: View {
    
    @ObservedObject private(set) var viewModel: ArticlesViewModelWrapper
    
    var body: some View {
        VStack {
            AppBar()

            switch viewModel.articlesState {
            case is ArticleState.Success:
                let articles = (viewModel.articlesState as! ArticleState.Success).articles
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(articles, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            case is ArticleState.Error:
                let errorMessage = (viewModel.articlesState as! ArticleState.Error).errMsg ?? ""
                ErrorMessage(message: errorMessage)
            case is ArticleState.Loading:
                if (viewModel.articlesState as! ArticleState.Loading).isLoading {
                    Loader()
                }
            default:
                EmptyView()
            }
        }.onAppear{
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct ArticleItemView: View {
    var article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            Text(article.title).font(.title3.bold()).lineLimit(2)
            Text(article.description_).font(.body).lineLimit(2)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}

