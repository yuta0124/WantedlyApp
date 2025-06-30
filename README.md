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

#### 募集一覧画面 - 検索
![before](https://github.com/user-attachments/assets/d912c7cf-fad5-4672-b0f2-c1b1720729ee)

#### 募集一覧画面 - ページネーション
![after](https://github.com/user-attachments/assets/47b95488-0986-40f5-8ae3-ba3879d647bd)

#### 募集詳細画面
![after](https://github.com/user-attachments/assets/7ff92802-ff86-4eb4-8772-6fc7b9b71e81)

#### ブックマーク機能及び画面間情報同期
![bookmark](https://github.com/user-attachments/assets/0400dea6-bfce-40b6-8933-e0b971df0c08)

#### エラー表示
| 一覧画面 | エラー時表示 |
| --- | --- |
|<img width=250 src=https://github.com/user-attachments/assets/56779979-d5c1-4730-808c-278280767897>|<img width=250 src=https://github.com/user-attachments/assets/700ae1c1-d8e2-4ffb-bb41-b16e601c5e82>|
