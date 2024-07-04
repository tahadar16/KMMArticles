import SwiftUI
import shared

struct ContentView: View {
    
    @State private var shouldOpenAbout = false

	var body: some View {
        let articleScreen = ArticlesScreen(viewModel: .init())
        NavigationStack {
            articleScreen
                .toolbar {
                    ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                        } .popover(isPresented: $shouldOpenAbout, content: {
                            AboutScreen()
                        })
                    }
                }
        }.refreshable {
            articleScreen.viewModel.articlesViewModel.loadData(forceLoad: true)
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
