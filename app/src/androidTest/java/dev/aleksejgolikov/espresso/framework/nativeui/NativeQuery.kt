package dev.aleksejgolikov.espresso.framework.nativeui

import androidx.test.uiautomator.By
import androidx.test.uiautomator.BySelector

class NativeElement(
    internal val selector: BySelector,
) {
    fun andRes(resourceId: String) = NativeElement(selector.res(resourceId))

    fun andText(text: String) = NativeElement(selector.text(text))

    fun andTextContains(text: String) = NativeElement(selector.textContains(text))

    fun andDesc(description: String) = NativeElement(selector.desc(description))

    fun andClazz(cls: String) = NativeElement(selector.clazz(cls))

    fun andPkg(packageName: String) = NativeElement(selector.pkg(packageName))
}

object NativeQuery {
    fun res(resourceId: String) = NativeElement(By.res(resourceId))

    fun text(value: String) = NativeElement(By.text(value))

    fun textContains(value: String) = NativeElement(By.textContains(value))

    fun desc(value: String) = NativeElement(By.desc(value))

    fun descContains(value: String) = NativeElement(By.descContains(value))

    fun clazz(cls: String) = NativeElement(By.clazz(cls))

    fun pkg(packageName: String) = NativeElement(By.pkg(packageName))

    fun resAndText(
        resourceId: String,
        text: String,
    ) = res(resourceId).andText(text)

    fun resAndDesc(
        resourceId: String,
        description: String,
    ) = res(resourceId).andDesc(description)

    fun textAndClazz(
        text: String,
        cls: String,
    ) = text(text).andClazz(cls)

    fun pkgAndRes(
        packageName: String,
        resourceId: String,
    ) = pkg(packageName).andRes(resourceId)
}
