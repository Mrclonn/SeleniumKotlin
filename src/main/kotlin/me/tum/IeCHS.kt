package me.tum

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import java.time.Duration

class IeCHS {
    init {
        System.setProperty("webdriver.ie.driver", "src/main/kotlin/drivers/IEDriverServer.exe")
    }

    private val capabilities = InternetExplorerOptions().ignoreZoomSettings()
    private val driver = InternetExplorerDriver(capabilities)
    private val actions = Actions(driver)


    private fun driverOptions() {
        driver.get(urlCHSTest2)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30))
        actions.keyDown(Keys.CONTROL).sendKeys("0").keyUp(Keys.CONTROL).release().build().perform() // Zoom == 100%
    }

    private fun formRuClaimCommon() {
        val department = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: document().department')]"))
        val selectDepartment = Select(department)
        selectDepartment.selectByValue("TER | Региональная компания ГК Таврида Электрик")
        val document = driver.findElement(By.xpath("//input[contains(@data-bind, 'externalDocumentNumber')]"))
        document.sendKeys("TestDocSelenium")
        val customer = driver.findElement(By.xpath("//input[@data-bind= 'textInput: customer']"))
        customer.sendKeys("tev")
        val serialNumber = driver.findElement(By.xpath("//input[contains(@data-bind, 'moduleSerialNumber')]"))
        serialNumber.sendKeys(serialNum)
        val findModule = driver.findElement(By.xpath("//span[text()= 'Find Module...']"))
        findModule.click()
        Thread.sleep(8000)
        val quantity = driver.findElement(By.xpath("//input[contains(@data-bind, 'modulesQuantity')]"))
        quantity.sendKeys("1")
        val unit = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: measurementUnit')]"))
        val selectUnit = Select(unit)
        selectUnit.selectByValue("Шт | Штука")
        val failureCategory = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: failureCategory')]"))
        val selectFailureCategory = Select(failureCategory)
        selectFailureCategory.selectByIndex(1)
        val failureStage = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: failureStage')]"))
        val selectFailureStage = Select(failureStage)
        selectFailureStage.selectByIndex(1)
        val importanceRU = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: importanceType')]"))
        val selectImportanceRU = Select(importanceRU)
        selectImportanceRU.selectByValue("Low")
        val assumedResponsibilityRU = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: ruOpinionResponsibleParty')]"))
        val selectAssumedResponsibilityRU = Select(assumedResponsibilityRU)
        selectAssumedResponsibilityRU.selectByIndex(1)
        Thread.sleep(8000)
    }

    fun createNewRuClaim() {
        driverOptions()
        val selectRuClaim = driver.findElement(By.xpath("//span[text()= 'RUClaims']"))
        selectRuClaim.click()
        val createRuClaim = driver.findElement(By.xpath("//div[@title= 'Создать Рекламацию РО']")) //div/span[text()= 'Создать']"))
        createRuClaim.click()
        formRuClaimCommon()
        val publishRuClaim = driver.findElement(By.xpath("//span[text()= 'Publish']"))
        publishRuClaim.click()

    }

    private fun formMANClaim() {

    }

    fun createNewMANClaim() {
        driverOptions()
        val selectMANClaim = driver.findElement(By.xpath("//span[text()= 'MANClaims']"))
        selectMANClaim.click()
        val createMANClaim = driver.findElement(By.xpath(""))
        createMANClaim.click()
        formMANClaim()
        val publishMANClaim = driver.findElement(By.xpath(""))
        publishMANClaim.click()
    }
}