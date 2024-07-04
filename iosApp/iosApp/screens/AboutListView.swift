//
//  AboutListView.swift
//  iosApp
//
//  Created by Taha Dar on 19/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AboutListView: View {
    private struct InfoItem: Hashable {
        let label: String
        let value: String
    }
    
    private let items: [InfoItem] = {
        let platform = Platform()
        platform.logDeviceInfo()
        
        var result: [InfoItem] = [
            .init(
                label: "Operating System",
                value: "\(platform.osName) \(platform.osVersion)"
            ),
            .init(label: "Device", value: platform.deviceModel),
            .init(label: "Density", value: "@\(platform.screenDensity)x")
        ]
        return result
    }()
    
    var body: some View {
        List {
            ForEach(items, id: \.self) { item in
                VStack(alignment: .leading) {
                    Text(item.label)
                        .font(.footnote)
                        .foregroundStyle(.secondary)
                    Text(item.value)
                        .font(.body)
                        .foregroundStyle(.primary)
                }
                .padding(.vertical, 4)
            }
        }
    }
}

#Preview {
    AboutListView()
}
