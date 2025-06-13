# WantedlyApp
当ブランチで今後の課題で記載した以下の内容を完了させる。
- Repository,ViewModelのテスコードを実装
- エラーハンドリング
- ブックマーク機能の実装
- コードのリファクタリング


## 開発環境

| 項目 | バージョン |
|---|---|
| AndroidStudio | `Android Studio Narwhal 2025.1.1 Nightly` |
| Android Gradle Plugin | `8.10.0` |
| kotlin | `2.0.21` |
| ksp | `2.0.21-1.0.27` |

## 対応OS

| 項目 | バージョン |
|---|---|
| minSdkVersion | 24 |
| targetSdkVersion | 36 |

## ライブラリ

| ライブラリ | 用途 | バージョン |
| --- | --- | --- |
| Compose Bom | UI | `2025.05.01` |
| navigationCompose | 画面遷移 | `2.9.0` |
| hilt | DI | `2.56.2` |
| hiltNavigationCompose | 画面遷移 | `1.2.0` |
| detekt | 静的解析 | `1.23.7` |
| twitterComposeRule | detektのルール補助 | `0.0.26` |
| arrow | アーキテクチャ | `2.0.1` |
| roborazzi | スクリーンショットテスト | `1.41.0` |
| robolectric | 同上 | `4.14.1` |
| composablePreviewScanner | 同上 | `0.5.1` |
| ktor | Kttpクライアント | `3.1.3` |
| coil | 画像読み込み | `3.2.0` |
| coilNetworkOkhttp | 同上 | `3.2.0` |

## 工夫点

#### スクリーンショットテスト
roborazziを使い、スクリーンショットテストを実施しています。
また、github actionsを使うことで、PRに対して差分がコメントされるようにすることで、他メンバーは視覚的にUIの更新部分を閲覧することができます。
<img width="1094" alt="スクリーンショット 2025-06-03 12 35 17" src="https://github.com/user-attachments/assets/9fc44b16-e563-4b45-a6fc-24346e34cad5" />

#### UICatalog
プロジェクトには新しいメンバーがジョインされることは少なくありません。
初めてアサインされた方々は、共通のコンポーネントにどんなものがあるのか、見落としがちな部分だと思います。
UICatalogを利用することで、視覚的のどんな共通コンポーネントがあるのかを閲覧することができ、早期のインプットを実現することができます。
<img width="250" src="https://github.com/user-attachments/assets/354fe5cb-0046-46cd-ad0a-fdd085dba13b" />

#### マルチモジュール
マルチモジュール構成にすることで、ビルドの高速化、関心の分離など、よりアーキテクチャを意識した環境にしました。
また、各モジュールのビルドロジックを共通化(:build-logicモジュール)することで、ボイラープレートを削減し、保守性の向上を図りました。

## アプリ動作
#### 募集一覧画面 - 検索
![before](https://github.com/user-attachments/assets/d912c7cf-fad5-4672-b0f2-c1b1720729ee)

#### 募集一覧画面 - ページネーション
![after](https://github.com/user-attachments/assets/47b95488-0986-40f5-8ae3-ba3879d647bd)

#### 募集詳細画面
![after](https://github.com/user-attachments/assets/7ff92802-ff86-4eb4-8772-6fc7b9b71e81)

#### ブックマーク機能及び画面間情報同期
![bookmark](https://github.com/user-attachments/assets/0400dea6-bfce-40b6-8933-e0b971df0c08)

