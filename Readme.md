# TextFieldBoxes


A new Material Design text field that comes in a box, based on [Google Material Design guidelines]


## Installation
In order to use it, you need to include it in your project.

#### Gradle:
```groovy
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
```
```groovy
dependencies {
    implementation 'com.github.TutorialsAndroid:MTextField:16.4.19'
}
```

## Usage

#### Table of Contents
1. [Basic](#basic)
2. [Enable / Disable](#enable)
3. [Helper Text & Error Text](#helper)
4. [Prefix & Suffix](#prefix)
5. [Max & Min Characters](#max)
6. [Icon Signifier](#icon)
7. [End Icon](#end)
8. [Clear Button](#clear)
9. [Custom Colors](#color)
10. [Dense Spacing](#dense)
11. [Always Show Hint](#hint)
12. [Text Change Watcher](#watcher)
13. [Dark Theme](#dark)
14. [Manual Validate Error](#validate)

#### <a id="basic"/>  1. Basic

Add `com.kinda.mtextfield.TextFieldBoxes` that contains a `com.kinda.mtextfield.ExtendedEditText` to your layout:

```xml
...
<com.kinda.mtextfield.TextFieldBoxes
    android:id="@+id/text_field_boxes"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:labelText="Label">

    <com.kinda.mtextfield.ExtendedEditText
        android:id="@+id/extended_edit_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</com.kinda.mtextfield.TextFieldBoxes>
...
```

#### <a id="enable"/>  2. Enable / Disable

`app:enabled` in xml or `setEnabled(boolean enabled)` in Java.

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:enabled="false"
    >
```

#### <a id="helper"/>  3. Helper Text & Error Text

_**NOTE:** setting helper or error text to anything **not empty** will make the bottom view (which contains the helper label) visible and increase the height of the TextFieldBoxes. So if you want to always keep the bottom view visible (height increased), set the helper text to `" "` when there should be nothing._

helper text: `app:helperText` in xml or `setHelperText(String helperText)` in Java.

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:helperText="Helper is here"
    >
```

error text: `setError(String errorText, boolean giveFocus)` in Java.

 Param `giveFocus` determines whether the field will gain focus when set error on. If `true`, the field gains focus immediately.

*NOTE: Error will be removed when the text changes (input or delete).*

```java
setError("Error message");
```

#### <a id="prefix"/>  4. Prefix & Suffix

_**! NOTE:** Prifix and Suffix attributes should be set to `ExtendedEditText`._

Use `app:prefix` in xml or `setPrefix(String prefix)` in Java to set the unselectable prefix text at the start of the field.

Use `app:suffix` in xml or `setSuffix(String suffix)` in Java to set the unselectable suffix text at the end of the field.

```xml
<com.kinda.mtextfield.ExtendedEditText
    ...
    app:prefix="$ "
    >
```

```xml
<com.kinda.mtextfield.ExtendedEditText
    ...
    app:suffix="\@gmail.com"
    >
```

#### <a id="max"/>  5. Max & Min Characters

_**NOTE:** setting max or min character will make the bottom view (which contains the counter label) visible and increase the height of the TextFieldBoxes._

Use `app:maxCharacters` in xml or `setMaxCharacters(int maxCharacters)` in java code to set the max characters count. Use `removeMaxCharacters()` in java code to remove the limit.

Use `app:minCharacters` in xml or `setMinCharacters(int minCharacters)` in java code to set the min characters count. Use `removeMinCharacters()` in java code to remove the limit.

The color of the bottom line will turn to `errorColor` (red by default) when exceeding max or min characters limit. `0`, as default, means no max or min characters limit.

*NOTE: Space and line feed will NOT count.*

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:maxCharacters="10"
    app:minCharacters="5"
    >
```

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:maxCharacters="5"
    >
```

#### <a id="icon"/>  6. Icon Signifier

Use `app:iconSignifier` in xml or `setIconSignifier(Int resourceID)` to set the icon that is shown in front of the TextFieldBoxes if you want there to be one.

You can use `setIsResponsiveIconColor(boolean isrResponsiveIconColor)` in Java code to set whether the icon will change its color when gaining or losing focus as the label text and the bottomLine do.
_**NOTE that if `true`, the icon's color will always be `HighlightColor` (the same as the bottomLine) that will turn gray when losing focus. If `false`, the icon will always be in `primaryColor`.**_

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:iconSignifier="@drawable/ic_vpn_key_black_24dp"
    >
```

#### <a id="end"/>  7. End Icon

Use `app:endIcon` in xml or `setEndIcon(Int resourceID)` to set the icon of the ImageButton that is shown at the end of the field if you want there to be one.

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:endIcon="@drawable/ic_mic_black_24dp"
    >
```

To make it useful (trigger voice input, dropdown event), you would like to use `getEndIconImageButton()` in Java code to set, for example, what will happen when it's clicked (with `.setOnClickListener()`), or anything else you want.

```java
final TextFieldBoxes textFieldBoxes = findViewById(R.id.text_field_boxes);
textFieldBoxes.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        // What you want to do when it's clicked
    }
});
```

#### <a id="clear"/>  8. Clear Button

Use `app:hasClearButton` in xml or `setHasClearButton(boolean hasClearButton)` to set whether to show the clear button.

If `true`, a clear button will be shown at the end when there are characters (**ANY** character) entered in the field.

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:hasClearButton="true"
    >
```

#### <a id="color"/>  9. Custom Colors

*Primary Color* will be used for the color of the underline, the floating label text and the icon signifier **when HAVING focus**. You can use `app:primaryColor` in xml or `setPrimaryColor(int colorRes)` in Java. Current theme `Primary Color` by default.

*Secondary Color* will be used for the color of the underline, the floating label text and the icon signifier **when NOT HAVING focus**. You can use `app:secondaryColor` in xml or `setSecondaryColor(int colorRes)` in Java. Current theme `textColorTertiary` by default.

*Error Color* will be used for the color that indicates error (e.g. exceeding max characters, `setError()`). You can use `app:errorColor` in xml or `setErrorColor(int colorRes)` in Java. `A400 red` by default.

*Helper Text Color* will be used for the color of the helper text. You can use `app:helperTextColor` in xml or `setHelperTextColor(int colorRes)` in Java. `54% black` by default.

*Panel Background Color* will be used for the color of panel at the back. You can use `app:panelBackgroundColor` in xml or `setPanelBackgroundColor(int colorRes)` in Java. `6% black` by default. *NOTE that the default color, as in the guideline, is the black (`#000000`) color with the transparency of 6%, so when you're customizing and want it to still be transparent you have to set a color with transparency.*

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:primaryColor="#1B5E20"
    app:errorColor="#ddaa00"
    app:helperTextColor="#795548"
    app:panelBackgroundColor="#ffe8e8"
    >
```

*Ripple Color* will be used for the ripple effect when the view is clicked. You can customize it in your `styles.xml` by adding the following:

```xml
<style name="TextFieldBoxes">
    <item name="android:colorControlHighlight" tools:targetApi="lollipop">YOUR_COLOR</item>
</style>
```

then set this as the theme for your TextFieldBoxes:
```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    android:theme="@style/TextFieldBoxes"
    >
```

#### <a id="dense"/>  10. Dense Spacing

You can make the layout compact by using a dense verticle spacing to improve user experience in some cases.

Use `app:useDenseSpacing` in xml or `setUseDenseSpacing(boolean useDenseSpacing)` to set whether the field uses a dense spacing between its elements.

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:useDenseSpacing="true"
    >
```

#### <a id="hint"/>  11. Always Show Hint

Sometimes you may want the label and the hint to show different messages, but need the hint to always be shown (instead being blocked by the label when losing focus).

Use `app:alwaysShowHint` in xml or `setAlwaysShowHint(boolean alwaysShowHint)` to set whether the label is fixed at top when there's a hint in the EditText.

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:alwaysShowHint="true"
    >
```

#### <a id="watcher"/>  12. Text Change Watcher

It is strongly recommanded to use `setSimpleTextChangeWatcher()` to listen the event of changing text instead of `addTextChangedListener()`.

This has the following advantages:
1. You don't need to implement `beforeTextChanged()` and `onTextChanged()` method when unnecessary.
2. Avoids potential unexpected behavior, by guaranteeing your code to run after the default processes (remove error, update counter text, etc.) are finished.
3. When the view is recycled, no manual remove call is needed.

e.g.
```java
final TextFieldBoxes textFieldBoxes = findViewById(R.id.text_field_boxes);
textFieldBoxes.setSimpleTextChangeWatcher(new SimpleTextChangedWatcher() {
    @Override
    public void onTextChanged(String theNewText, boolean isError) {
        // What you want to do when text changes
    }
});
```

#### <a id="dark"/>  13. Dark Theme

TextFieldBoxes use the color attributes within the current theme and will automatically change its color to fit the dark theme without additional settings.

#### <a id="validate"/>  14. Manual Validate Error

By default, the error state of the field is validated each time the text changes and also at time of construction. This means a field with a minimum length requirement will start in the Error state.

Setting `app:manualValidateError` to `true` will make the field validate error only when `validate()` is called.

```xml
<com.kinda.mtextfield.TextFieldBoxes
    ...
    app:manualValidateError="true"
    >
```
```Java
final TextFieldBoxes textFieldBoxes = findViewById(R.id.text_field_boxes);
// The error state will only be updated when this is called
textFieldBoxes.validate()
```
​
## All Attributes

### ExtendedEditText

##### Texts

| Attribute | Description |
| --- | --- |
| `app:prefix` | Prefix Text |
| `app:suffix` | Suffix Text |

##### Colors

| Attribute | Description | Default |
| --- | --- | --- |
| `app:prefixTextColor` | Prefix text color | Current theme `textColorTertiary` |
| `app:suffixTextColor` | Suffix text color | Current theme `textColorTertiary` |

### TextFieldBoxes

##### Texts

| Attribute | Description |
| --- | --- |
| `app:labelText` | Floating label text at the top |
| `app:helperText` | Helper text at the bottom |

##### Colors

| Attribute | Description | Default |
| --- | --- | --- |
| `app:helperTextColor` | Helper text color | Current theme `textColorTertiary` |
| `app:counterTextColor` | Counter text color | Current theme `textColorTertiary` |
| `app:errorColor` | The color that is used to indicate error (e.g. exceeding max characters, `setError()`) | `A400 red` |
| `app:primaryColor` | The color for the underline, the floating label text and the icon signifier **when HAVING FOCUS** | Current theme `colorPrimary` |
| `app:secondaryColor` | The color for the underline, the floating label text and the icon signifier **when NOT HAVING FOCUS** | Current theme `textColorTertiary` |
| `app:panelBackgroundColor` | The color for the panel at the back | 6% Current theme `colorForeground` |

##### Icons

| Attribute | Description | Default |
| --- | --- | --- |
| `app:iconSignifier` | The resource ID of the icon before the TextFieldBoxes | `0` |
| `app:endIcon` | The resource ID of the icon at the end of the field | `0` |
| `app:isResponsiveIconColor` | whether the icon signifier will change its color when gaining or losing focus as the label and the bottomLine do | `True` |

##### Characters counter

| Attribute | Description | Default |
| --- | --- | --- |
| `app:maxCharacters` | Max characters count limit. `0` means no limit | `0` |
| `app:minCharacters` | Min characters count limit. `0` means no limit | `0` |

##### Others

| Attribute | Description | Default |
| --- | --- | --- |
| `app:enabled` | Whether the text field is enabled | `True` |
| `app:hasClearButton` | Whether to show the clear button at the end of the EditText | `False` |
| `app:hasFocus` | Whether the EditText is having the focus | `False` |
| `app:alwaysShowHint` | Whether the label is fixed at top when there's a hint in the EditText | `False` |
| `app:useDenseSpacing` | Whether the field uses a dense spacing between its elements | `False` |
| `app:manualValidateError` | Whether to validate error state only when `validate()` is called | `False` |
​

## License

    Copyright 2019 MTextField

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.