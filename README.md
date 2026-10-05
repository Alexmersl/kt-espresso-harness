# kt-espresso-harness

Android UI test **framework**: Espresso + Compose Test + UI Automator + ADB.

## Layers

| Layer | Entry | Locators | Actions |
|---|---|---|---|
| Espresso (`View`) | `ViewQuery` | id, text, desc, hint, tag, class, `idAndText`, descendant/child/sibling | `tap`, `type`, `waitShown`, swipe, recycler scroll |
| Compose | `compose.byTag` / `byText` / `byDesc` | tag, text, desc, `byTagAndText`, `byTextUnderTag` | `tap`, `type`, `waitShown`, `scrollToText/Tag/Index` |
| Native (UI Automator) | `NativeQuery` | res, text, desc, clazz, pkg, `resAndText` | `tap`, `type`, `swipe`, `scrollUntilFound` |
| Device | `Adb` / `scripts/adb-network.sh` | — | wifi/data/airplane on/off |

Page objects: private fields are locators; tests call screen methods only.

```kotlin
private val banner = ViewQuery.idAndText(R.id.host_banner, "Host banner")
private val row = compose.byTag(Tags.HOME_LOGIN_ROW)
private val nativeHost = NativeQuery.res("$pkg:id/compose_host")

banner.waitShown()
row.tap()
nativeHost.exists()
Adb.disableInternet()
```

Network from a host shell:

```bash
./scripts/adb-network.sh net-off
./scripts/adb-network.sh net-on
```

## Run

```bash
./gradlew :app:connectedDebugAndroidTest
```

## License

MIT — see `LICENSE`.
