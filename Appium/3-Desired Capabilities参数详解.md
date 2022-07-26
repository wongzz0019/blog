## Appium 服务器初始化参数（Capability）

- Desired Capabilities 是由 keys 和 values 组成的 JSON 对象。

```python
desired_caps = {
    "platformName": "Android",
    "platformVersion": "6.0",
    "deviceName": "honor",
    "appPackage": "",
    "appActivity": "",
    "noReset": True
}
```





### 通用功能

此参数支持多种驱动程序

| 键                             | 描述                                                         | 值                                                           |
| ------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `automationName`               | 自动化测试的引擎                                             | `Appium` （默认）或者 `UiAutomator2`, `Espresso`, 或者 `UiAutomator1`(Android), 或者 `XCUITest`(IOS), 或者`YouiEngine` (You.i Engine) |
| `platformName`                 | 使用的手机操作系统                                           | `iOS`, `Android`, 或者 `FirefoxOS`                           |
| `platformVersion`              | 手机操作系统的版本                                           | 例如  `7.1`, `4.4`                                           |
| `deviceName`                   | 使用的手机或模拟器类型                                       | `iPhone Simulator`, `iPad Simulator`, `iPhone Retina 4-inch`, `Android Emulator`, `Galaxy S4`, 等等.... 在 iOS 上，使用 Instruments 的 `instruments -s devices` 命令可返回一个有效的设备的列表。在 Andorid 上虽然这个参数目前已被忽略，但仍然需要添加上该参数。 |
| `app`                          | 本地绝对路径_或_远程 http URL 所指向的一个安装包（`.ipa`(IOS),`.app`(IOS模拟器),`.apk`(安卓),`.apks`(安卓app打包),或 `.zip` 文件）。Appium 将其安装到合适的设备上。请注意，如果您指定了 `appPackage` 和 `appActivity` 参数（见下文），Android 则不需要此参数了。`UiAutomator2`和`XCUITest`允许在没有`app`或`appPackage`的情况下启动会话。该参数也与 `browserName` 不兼容。见 [此文](https://www.kancloud.cn/testerhome/appium_docs_cn/2001885) 关于 `.apks` 文件。 | `/abs/path/to/my.apk` 或 `http://myapp.com/app.ipa`          |
| `otherApps`                    | 在运行测试之前要安装的应用程序或应用程序列表(作为JSON排列)。请注意，`Espresso`的`automationName`和iOS真机不支持。 | 例如 `"/path/to/app.apk"`, `https://www.example.com/url/to/app.apk`, `["http://appium.github.io/appium/assets/TestApp9.4.app.zip", "/path/to/app-b.app"]` |
| `browserName`                  | 做自动化时使用的浏览器名字。如果是一个应用则只需填写个空的字符串。 | 'Safari' 对应 iOS，'Chrome', 'Chromium', 或 'Browser' 则对应 Android |
| `newCommandTimeout`            | 用于客户端在退出或者结束 session 之前，Appium 等待客户端发送一条新命令所花费的时间（秒为单位）。 | 例如  `60`                                                   |
| `language`                     | iOS (仅XCUITest driver )和 Android 为模拟器设置语言。        | 例如  `fr`                                                   |
| `locale`                       | iOS (仅XCUITest driver )和 Android 为模拟器设置所在区域，fr_CA`对应 iOS，`CA`对应 Android(国家名称缩写)。|例如`fr_CA`,`CA` |                                                              |
| `udid`                         | 连接真机的唯一设备号                                         | 例如  `1ae203187fc012g`                                      |
| `orientation`                  | (仅支持模拟器) 模拟器当前的方向                              | `竖屏` 或 `横屏`                                             |
| `autoWebview`                  | 直接转换到 Webview 上下文（context）。默认值为 `false`       | `true`, `false`                                              |
| `noReset`                      | 在当前 session 下不会重置应用的状态。见[此文](https://www.kancloud.cn/testerhome/appium_docs_cn/2001887) | `true`, `false`                                              |
| `fullReset`                    | 执行一个完整的重置. 见 [此文](https://www.kancloud.cn/testerhome/appium_docs_cn/2001887) | `true`, `false`                                              |
| `eventTimings`                 | 启用或禁用各种Appium-internal事件的时间报告 (例如 每个命令的开始和结束，等等)。 默认值为 `false`。启用, 用 `true`。然后，在查询当前会话的响应上，计时被报告为`events`属性。 见响应结构[此文](https://www.kancloud.cn/testerhome/appium_docs_cn/2001915) | 例如 `true`                                                  |
| `enablePerformanceLogging`     | (仅Web和webview) 启用Chromedriver(Android)或Safari(iOS)的性能日志 (默认值为 `false`) | `true`, `false`                                              |
| `printPageSourceOnFindFailure` | 当查找操作失败时，打印当前页源文件。 默认值为 `false`        | 例如 `true`                                                  |
| `clearSystemFiles`             | 在会话结束时删除所有生成的文件。 默认值为 `false`            | `true`, `false`                                              |

- [更新设置](https://www.kancloud.cn/testerhome/appium_docs_cn/2001853#更新设置)

| 键                      | 描述                                                         | 值                                                           |
| ----------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `settings[settingsKey]` | 在会话创建时更新 [更新设置](https://github.com/appium/appium/blob/master../advanced-concepts/settings.md). | 例如 `'settings[mjpegScalingFactor]': 10`, `'settings[shouldUseCompactResponses]': true` |



### Android 独有

这些参数只能在基于Android drivers上使用 (例如 [UiAutomator2](https://www.kancloud.cn/testerhome/appium_docs_cn/2001675))。

| 键                                | 描述                                                         | 值                                                           |
| --------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `appActivity`                     | Activity 的名字是指从你的包中所要启动的 Android acticity。他通常需要再前面添加`.` （例如  使用 `.MainActivity` 代替 `MainActivity`）。默认情况下，此参数是从软件包manifest中接收的（action: android.intent.action.MAIN , category: android.intent.category.LAUNCHER） | `MainActivity`, `.Settings`                                  |
| `appPackage`                      | 运行的 Android 应用的包名。默认情况下，此参数是从软件包manifest中接收的。（@package属性值） | `com.example.android.myApp`, `com.android.settings`          |
| `appWaitActivity`                 | Activity name/names，逗号分隔，用于你要等待的Android Activity。 ：默认情况下，此参数的值和`appActivity`相同. 如果你的参数中有`appActivity`和`appPackage`，必须设置activity以防和`appActivity`不同。你也可以使用通配符(`*`)。 | `SplashActivity`, `SplashActivity,OtherActivity`, `*`, `*.SplashActivity` |
| `appWaitPackage`                  | 用于等待启动的 Android 应用的包。默认情况下，此功能的值与`appActivity`的值相同 | `com.example.android.myApp`, `com.android.settings`          |
| `appWaitDuration`                 | 用于等待 appWaitActivity 启动的超时时间（以毫秒为单位）（默认值为  `20000`) | `30000`                                                      |
| `deviceReadyTimeout`              | 用于等待模拟器或真机准备就绪的超时时间                       | `5`                                                          |
| `allowTestPackages`               | 允许安装测试包(mainfest中`android:testOnly="true"`) 默认值为`false` | `true` 或 `false`                                            |
| `androidCoverage`                 | 用于执行测试的 instrumentation 类。 传送 `-w` 参数到如下命令 `adb shell am instrument -e coverage true -w` | `com.my.Pkg/com.my.Pkg.instrumentation.MyInstrumentation`    |
| `androidCoverageEndIntent`        | 一个自己实现的广播操作，用于将覆盖率转储到文件系统中。传送 `-a` 参数到如下命令 `adb shell am broadcast -a` | `com.example.pkg.END_EMMA`                                   |
| `androidDeviceReadyTimeout`       | 用于等待设备在启动应用后准备就绪的超时时间。以秒为单位。     | 例如  `30`                                                   |
| `androidInstallTimeout`           | 用于等待在设备中安装 apk 所花费的时间（以毫秒为单位）。默认值为 `90000` | 例如  `90000`                                                |
| `androidInstallPath`              | 安装前将在其中推送apk的设备上的目录名。默认值为`/data/local/tmp` | 例如 `/sdcard/Downloads/`                                    |
| `adbPort`                         | 用来连接 ADB 服务器的端口（默认值为 `5037`）                 | `5037`                                                       |
| `systemPort`                      | `systemPort` 用于连接 [appium-uiautomator2-server](https://github.com/appium/appium-uiautomator2-server) 或 [appium-espresso-driver](https://github.com/appium/appium-espresso-driver). *appium-uiautomator2-server_通常情况默认值为 `8200` ，可 从`8200`到`8299`选择一个端口。对于_appium-espresso-driver*，默认值为`8300`，端口地址可从`8300`到`8399`中选择一个。当并行运行测试时，必须调整端口以避免冲突。 参见 [并行测试](https://github.com/appium/appium/blob/master../advanced-concepts/parallel-tests.md#parallel-android-tests) | 例如 `8201`                                                  |
| `remoteAdbHost`                   | 可选的远程ADB服务器主机                                      | 例如 192.168.0.101                                           |
| `androidDeviceSocket`             | 开发工具的 socket 名称。只有在被测应用是一个使用 Chromium 内核的浏览器时才需要。socket 会被浏览器打开，然后 Chromedriver 把它作为开发者工具来进行连接。 | 例如  `chrome_devtools_remote`                               |
| `avd`                             | 被启动 avd 的名字                                            | 例如  `api19`                                                |
| `avdLaunchTimeout`                | 用于等待 avd 启动并连接 ADB 的超时时间（以毫秒为单位），默认值为 `60000`。 | `300000`                                                     |
| `avdReadyTimeout`                 | 用于等待 avd 完成启动动画的超时时间（以毫秒为单位），默认值为 `120000`。 | `300000`                                                     |
| `avdArgs`                         | 启动 avd 时使用的额外参数                                    | 例如  `-netfast`                                             |
| `useKeystore`                     | 使用自定义的 keystore 给 apk 签名，默认值为 `false`          | `true`或`false`                                              |
| `keystorePath`                    | 自定义 keystore 的路径, 默认路径为 ~/.android/debug.keystore | 例如  `/path/to.keystore`                                    |
| `keystorePassword`                | 自定义 keystore 的密码                                       | 例如  `foo`                                                  |
| `keyAlias`                        | key 的别名                                                   | 例如  `androiddebugkey`                                      |
| `keyPassword`                     | key 的密码                                                   | 例如  `foo`                                                  |
| `chromedriverExecutable`          | webdriver 可执行文件的绝对路径（如果 Chromium 内嵌一个自己提供的 webdriver，则应使用他去替换掉 Appium 自带的 chromedriver） | `/abs/path/to/webdriver`                                     |
| `chromedriverArgs`                | 当由Appium运行时，要传递给chromedriver二进制文件的参数数组。默认情况下，除了Appium内部使用的以外，不会添加CLI参数 (例如 `--url-base`, `--port`, `--adb-port`, and `--log-path`. | 例如 `["--disable-gpu", "--disable-web-security"]`           |
| `chromedriverExecutableDir`       | 用于查找Chromedriver可执行文件的目录的绝对路径，用于自动发现兼容的Chromedriver。忽略 `chromedriverUseSystemExecutable` 为 `true` | `/abs/path/to/chromedriver/directory`                        |
| `chromedriverChromeMappingFile`   | 将Chromedriver版本映射到它所支持的最小Chrome的文件的绝对路径。忽略`chromedriverUseSystemExecutable` 为`true` | `/abs/path/to/mapping.json`                                  |
| `chromedriverUseSystemExecutable` | 当 `true`时, 绕过自动Chromedriver配置，使用随Appium下载的版本。 忽略 `chromedriverExecutable` 已设置。 默认值为 `false` | 例如 `true`                                                  |
| `autoWebviewTimeout`              | 用于等待 Webview 上下文（context）激活的时间（以毫秒为单位）。默认值为 `2000` | 例如  `4`                                                    |
| `chromedriverPort`                | 数字端口启动Chromedriver。请注意，不建议使用此功能，因为在有多个webview的情况下，它会导致未定义的行为。默认情况下，Appium会找到一个空闲端口。 | 例如`8000`                                                   |
| `chromedriverPorts`               | Appium用于与Chromedrivers通信的有效端口列表。这个功能支持多种webview场景。此参数的形式是一个数值端口数组，数组项本身可以是长度为2的数组，其中第一个元素是包含范围的开始，第二个元素是结束。默认情况下，Appium将使用任何空闲端口。 | 例如 `[8000, [9000, 9005]]`                                  |
| `ensureWebviewsHavePages`         | Appium是否应该增强它的页面webview检测，以保证任何webview上下文显示在上下文列表有活动的页面。 这可以防止在Chromedriver无法找到任何页面的情况下选择上下文时发生的错误。 默认值为 `false` | 例如 `true`                                                  |
| `webviewDevtoolsPort`             | 为了支持`ensureWebviewsHavePages`特性，有必要打开一个TCP端口来与被测试设备上的webview通信。这个参数允许覆盖默认端口`9222`，以防多个会话同时运行(以避免端口冲突)，或者默认端口不适合您的系统 | 例如 `9543`                                                  |
| `enableWebviewDetailsCollection`  | 自Appium1.18.0+起启用通过`/json/version`CDP（Chrome开发者协议）端点收集详细的WebView信息。这有助于正确匹配支持给定WebView的Chromedriver版本。如果没有启用此标志，Appium会尝试根据相应已安装软件包的版本来猜测WebView的版本。(自定义web视图[失败详见](https://github.com/appium/appium/issues/13918)) 默认值为 `false` | `true` 或 `false`                                            |
| `dontStopAppOnReset`              | 在使用adb启动应用程序之前，不要停止被测应用程序的进程。 如果被测试的应用程序是由另一个锚定应用程序创建的，则将其设置为false，则在使用adb启动测试应用程序的过程中，锚定应用程序的过程仍然可以运行。换句话说，当`dontStopAppOnReset`设置为`true`时，在`adb shell am start`调用中将不包括`-S`标志。省略此参数或将其设置为`false`时，包含`-S`标志。默认值为 `false` | `true` 或 `false`                                            |
| `unicodeKeyboard`                 | 使用 Unicode 输入法。 默认值为 `false`                       | `true`或`false`                                              |
| `resetKeyboard`                   | 在设定了 `unicodeKeyboard` 关键字的 Unicode 测试结束后，重置输入法到原有状态。如果单独使用，将会被忽略。默认值为 `false` | `true`或`false`                                              |
| `noSign`                          | 跳过检查和对应用进行 debug 签名的步骤。仅适用于 UiAutomator。 默认值为 `false` | `true`或`false`                                              |
| `ignoreUnimportantViews`          | 调用 uiautomator 的函数 `setCompressedLayoutHierarchy()`。由于 Accessibility 命令在忽略部分元素的情况下执行速度会加快，这个关键字能加快测试执行的速度。被忽略的元素将不能够被找到，因此这个关键字同时也被实现成可以随时改变的 *设置 ( settings )*。 默认值为 `false` | `true` 或 `false`                                            |
| `disableAndroidWatchers`          | 禁用 android 监视器（watchers）。监视器用于见识应用程序的无响应状态（anr）和崩溃（crash），禁用会降低 Android 设备或模拟器的 CPU 使用率。该 capability 仅在使用 UiAutomator 时有效。默认设置为 `false`。 | `true` 或 `false`                                            |
| `chromeOptions`                   | 允许对 ChromeDriver 传 chromeOptions 的参数。了解更多信息请查阅 [chromeOptions](https://sites.google.com/a/chromium.org/chromedriver/capabilities) | `chromeOptions: {args: ['--disable-popup-blocking']}`        |
| `recreateChromeDriverSessions`    | 当移除非 ChromeDriver webview时，终止掉 ChromeDriver 的 session。默认设置为 `false` | `true`或`false`                                              |
| `nativeWebScreenshot`             | 在 web 的上下文（context），使用原生（native）的方法去截图，而不是用过代理的 ChromeDriver。默认值为 `false` | `true`或`false`                                              |
| `androidScreenshotPath`           | 在设备中截图被保存的目录名。默认值为 `/data/local/tmp`       | 例如  `/sdcard/screenshots/`                                 |
| `autoGrantPermissions`            | 让Appium自动确定您的应用需要哪些权限，并在安装时将其授予应用。默认设置为 `false`。如果`noReset`参数为`true`，此参数无效。 | `true`或`false`                                              |
| `networkSpeed`                    | 模拟设置网络速度。指定最大的网络上传和下载速度。 默认值为 `full` | `['full', 'gsm', 'edge', 'hscsd', 'gprs', 'umts', 'hsdpa', 'lte', 'evdo']` 详见 [-netspeed选项](https://developer.android.com/studio/run/emulator-commandline.html) |
| `gpsEnabled`                      | 在开始会话之前，为模拟器切换gps位置提供程序。 默认情况下，仿真器将根据配置方式启用或不启用此选项。 | `true` 或 `false`                                            |
| `isHeadless`                      | 设置此参数为`true`， 当不需要显示设备显示时，无头运行仿真器。 默认值为 `false` 。 `_isHeadless_`也支持 iOS, 参见 XCUITest-specific 参数. | 例如 `true`                                                  |
| `adbExecTimeout`                  | 用于等待adb命令执行的超时(毫秒)。默认值为 `20000`            | 例如 `50000`                                                 |
| `localeScript`                    | 设置地区 [见此文](https://developer.android.com/reference/java/util/Locale) | 例如 `"Cyrl"` (Cyrillic)                                     |
| `skipDeviceInitialization`        | 跳过设备初始化，其中包括i.a.:设置app的安装运行或权限设置。当设备已经被用于自动化并且为下一个自动化做好了准备时，可以用来提高启动性能。 默认值为 `false` | `true` 或 `false`                                            |
| `chromedriverDisableBuildCheck`   | 为Chrome webview测试设置chromedriver标志`--disable-build-check`。 | `true` 或 `false`                                            |
| `skipUnlock`                      | 在会话创建期间跳过解锁。 默认值为`false`                     | `true` 或 `false`                                            |
| `unlockType`                      | 使用特定的锁定模式解锁目标设备，而不仅仅是使用助手应用程序唤醒设备。 和 `unlockKey` 参数一起使用. 默认值为 undefined. `fingerprint` 仅支持Android 6.0+ 和 模拟器。 详见 [解锁](https://github.com/appium/appium-android-driver/blob/master/docs/UNLOCK.md) 。 | `['pin', 'password', 'pattern', 'fingerprint']`              |
| `unlockKey`                       | 使用`unlockType`解锁。                                       | 例如 '1111'                                                  |
| `autoLaunch`                      | 自动初始化被测应用。 如果这是`false`，则Appium不会安装/启动被测应用程序。 默认值为 `true` | `true` 或 `false`                                            |
| `skipLogcatCapture`               | 跳过开始捕获logcat。它可以提高网络等性能。与日志相关的命令不起作用。默认值为 `false`. | `true` 或 `false`                                            |
| `uninstallOtherPackages`          | 安装apks之前，软件包列表或`*` 卸载软件包。 `'*'` 会卸载所有第三方软件包，但Appium需要测试的软件包除外，例如 `io.appium.settings` 或者`io.appium.uiautomator2.server` ，因为Appium已经包含管理它们的逻辑。 | 例如 `"io.appium.example"`, `["io.appium.example1", "io.appium.example2"]`, `'*'` |
| `disableWindowAnimation`          | 如果值为`true`，则设置设备动画缩放为0。在会话完成后，Appium将动画缩放恢复到它的原始值。默认值为 `false` | `true`, `false`                                              |
| `remoteAppsCacheLimit`            | 设置远程高速缓存的apk的最大数量（默认为10），这些数量将被推送到被测设备的本地存储。 当使用同一组apk时，通过远程缓存apk可以避免每次需要重新安装apk都将其推送到远程文件系统时，可以加快顺序测试用例的执行速度。 将此功能设置为`0`以禁用缓存。 | 例如 `0`, `5`, `20`                                          |
| `buildToolsVersion`               | 将Android`build-tools`版本指定为不同于默认版本的版本（默认版本是使用最新版本）。如果您的环境使用alpha/beta构建工具，则使用非默认版本会很有帮助。 | 例如 `'28.0.3'`                                              |
| `androidNaturalOrientation`       | 允许在横向设备上正确处理方向。设置为`true`来改变竖屏和横屏的含义。默认值为 `false` | `true`, `false`                                              |
| `enforceAppInstall`               | 默认情况下，如果被测设备上已经存在此应用程序的更新或相同版本，则跳过该应用程序的安装。将此选项设置为`true`，将强制Appium始终独立于构建当前版本安装当前应用程序。默认值为`false`. | `true` , `false`                                             |
| `ignoreHiddenApiPolicyError`      | 自Appium 1.18.0+，忽略`Security exception: Permission denial`警告，并允许继续进行会话创建过程。 这个错误发生在Appium试图放松的时候 [隐藏的API](https://developer.android.com/distribute/best-practices/develop/restrictions-non-sdk-interfaces#how_can_i_enable_access_to_non-sdk_interfaces), 尽管有些设备有定制的固件拒绝这样的请求。 默认值为 `false`. | `true`, `false`                                              |
| `mockLocationApp`                 | 自Appium1.18.0+，设置应用程序的程序包标识符，用作系统模拟位置。此功能对模拟器没有影响。如果该参数设置为`null`或空字符串，则Appium将跳过模拟的位置提供程序设置过程。 默认 Appium 设置包标识符 (`io.appium.settings`)。 | 例如 `null`, `io.appium.settings`, `example.your.app`        |
| `logcatFormat`                    | 自Appium 1.18.0，设置logcat消息的输出格式 。 支持的格式见 [此文](https://github.com/appium/appium-adb/blob/master/lib/logcat.js). 详见更多[logcat#outputFormat](https://developer.android.com/studio/command-line/logcat#outputFormat)。默认值为`threadtime`. | 例如 `process`                                               |
| `logcatFilterSpecs`               | 自Appium 1.18.0，为logcat消息设置输出过滤规则。详见更多[logcat#filteringOutput](https://developer.android.com/studio/command-line/logcat#filteringOutput)。 [使用Logcat编写和查看日志](https://developer.android.com/studio/debug/am-logcat) | 例如 `['*:W', 'MyActivity:D']` (`MyActivity` is a tag)       |



#### UIAutomator (1 & 2)

这些参数在UIA 1 and 2中使用

| 键                        | 描述                                                         | 值                                                           |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `intentAction`            | 用于启动 activity 的 intent action。（默认值为  `android.intent.action.MAIN`) | 例如 `android.intent.action.MAIN`, `android.intent.action.VIEW` |
| `intentCategory`          | 用于启动 activity 的 intent category。（默认值为  `android.intent.category.LAUNCHER`) | 例如  `android.intent.category.LAUNCHER`, `android.intent.category.APP_CONTACTS` |
| `intentFlags`             | 用于启动 activity 的标识（flags）。（默认值为  `0x10200000`） | 例如  `0x10200000`                                           |
| `optionalIntentArguments` | 用于启动 activity 的额外 intent 参数。请查看 [Intent 参数](http://developer.android.com/reference/android/content/Intent.html) | 例如  `--esn <EXTRA_KEY>`, `--ez <EXTRA_KEY> <EXTRA_BOOLEAN_VALUE>`, 等等。 |



#### UIAutomator2 独有

这些参数仅在[UiAutomator2 Driver](https://www.kancloud.cn/testerhome/appium_docs_cn/2001675)上可用

| 键                                    | 描述                                                         | 值                                                           |
| ------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `appWaitForLaunch`                    | 尝试在会话创建中不带[-W](https://developer.android.com/studio/command-line/adb#am)选项的启动被测应用 。当由于`shell am start` 不响应而导致会话无法进行时，这可能会有所帮助。默认值为 `true`. | `false` 或 `true`                                            |
| `disableSuppressAccessibilityService` | 设置 [FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES](https://developer.android.com/reference/android/app/UiAutomation#FLAG_DONT_SUPPRESS_ACCESSIBILITY_SERVICES)允许现有的可访问性服务继续运行，并为Appium启动一个新服务。它有助于测试具有可访问性功能（例如话语提示）的被测应用。 如果未提供任何内容，则Appium将不会指定该标志。 该标志要求使用Android API Level 24+。 | `false` 或 `true`                                            |
| `mjpegServerPort`                     | 如果指定，这是本地端口，将绑定到`appium-uiautomator2-server`的MJPEG屏幕截图流。 可以与 `mjpegScreenshotUrl`一起使用。 `Integer` 默认值为 `null`。 | 任何 `Integer`, 建议: `9200` -> `9299` 以和 w/ `serverPort` 范围的一致性 |
| `skipServerInstallation`              | 跳过uiAutomator2服务器的安装，并从设备使用uiAutomator2服务器。 当设备上已安装正确版本的uiAutomator2服务器时，可用于提高启动性能。 默认值为 `false`. | `false` 或 `true`                                            |
| `uiautomator2ServerInstallTimeout`    | 等待uiAutomator2服务器安装的超时时间（以毫秒为单位）。 默认值为 `20000` | 例如 `20000`                                                 |
| `uiautomator2ServerLaunchTimeout`     | 等待uiAutomator2服务器启动的超时时间（以毫秒为单位）。 默认值为 `20000` | 例如 `20000`                                                 |
| `userProfile`                         | 如果提供了值，则将用户配置文件强制为给定参数。 它应该是一个整数。 | 例如 `11`                                                    |