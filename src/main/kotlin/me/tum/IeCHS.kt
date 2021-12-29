package me.tum

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import org.testng.annotations.Test
import java.time.Duration

open class IeCHS {
    init {
        System.setProperty("webdriver.ie.driver", "src/main/kotlin/drivers/IEDriverServer.exe")
    }

    private val capabilities = InternetExplorerOptions().ignoreZoomSettings()
    private val driver = InternetExplorerDriver(capabilities)
    private val actions = Actions(driver)


    private fun driverOptions() {
        driver.get(urlCHSTest2)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60))
        actions.keyDown(Keys.CONTROL).sendKeys("0").keyUp(Keys.CONTROL).release().build().perform() // Zoom == 100%
    }

    private fun formRuClaimCommon() {
        val department = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: document().department')]"))
        val selectDepartment = Select(department)
        selectDepartment.selectByIndex(23)// "TER | Региональная компания ГК Таврида Электрик"
        val document = driver.findElement(By.xpath("//input[contains(@data-bind, 'externalDocumentNumber')]"))
        document.sendKeys("TestDocSelenium${(1..100).random()}")
        val customer = driver.findElement(By.xpath("//input[@data-bind= 'textInput: customer']"))
        customer.sendKeys("tev")
        val serialNumber = driver.findElement(By.xpath("//input[contains(@data-bind, 'moduleSerialNumber')]"))
        serialNumber.sendKeys(serialNum)
        val quantity = driver.findElement(By.xpath("//input[contains(@data-bind, 'modulesQuantity')]"))
        quantity.clear()
        quantity.sendKeys("2")
        val unit = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: measurementUnit')]"))
        val selectUnit = Select(unit)
        selectUnit.selectByIndex(1)
        val failureCategory = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: failureCategory')]"))
        val selectFailureCategory = Select(failureCategory)
        selectFailureCategory.selectByIndex(1)
        val failureStage = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: failureStage')]"))
        val selectFailureStage = Select(failureStage)
        selectFailureStage.selectByIndex(2)
        val importanceRU = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: importanceType')]"))
        val selectImportanceRU = Select(importanceRU)
        selectImportanceRU.selectByIndex(1) // "Low"
        val assumedResponsibilityRU =
            driver.findElement(By.xpath("//select[contains(@data-bind, 'value: ruOpinionResponsibleParty')]"))
        val selectAssumedResponsibilityRU = Select(assumedResponsibilityRU)
        selectAssumedResponsibilityRU.selectByIndex(2)
    }

    private fun formRuClaimDecision() {
        val selectForm =
            driver.findElement(By.xpath("//div[@class='ui-widget-content slick-row even']/div[contains(@class, 'fa-caret-right selected')]"))
        selectForm.click()
        val selectDecision = driver.findElement(By.xpath("//a[@href= '#decision']"))
        selectDecision.click()
        val importanceMAN =
            driver.findElement(By.xpath("//select[contains(@data-bind, 'value: decision.importanceType')]"))
        val selectImportanceRU = Select(importanceMAN)
        selectImportanceRU.selectByIndex(3) // "High"
        val comment = driver.findElement(By.xpath("//div[@class= 'shortcut']"))
        comment.click()
        val causedByFailure = driver.findElement(By.xpath("//span[contains(@data-bind, 'moduleManufacturedOn')]"))
        causedByFailure.click()
        Thread.sleep(10000)
        val selectOk = driver.findElement(By.xpath("//div[@class='title']/span[text()= 'Ок']"))
        selectOk.click()
    }

    @Test(groups = ["CHS"])
    fun createNewRuClaim() {
        driverOptions()
        val selectRuClaim = driver.findElement(By.xpath("//span[text()= 'RUClaims']"))
        selectRuClaim.click()
        Thread.sleep(8000)
        val createRuClaim =
            driver.findElement(By.xpath("//div[@title= 'Создать Рекламацию РО']")) //div/span[text()= 'Создать']"))
        createRuClaim.click()
        formRuClaimCommon()
        val publishRuClaim = driver.findElement(By.xpath("//span[text()= 'Publish']"))
        publishRuClaim.click()
        Thread.sleep(5000)
        val closeFromCommon = driver.findElement(By.xpath("//button[@title='Close']"))
        closeFromCommon.click()
        formRuClaimDecision()
        val approveRuClaim = driver.findElement(By.xpath("//span[text()= 'Approve']"))
        approveRuClaim.click()
        Thread.sleep(5000)
        val closeFromDecision = driver.findElement(By.xpath("//button[@title='Close']"))
        closeFromDecision.click()
        driver.quit()
    }

    private fun formMANClaimCommon() {
        val department = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: document().department')]"))
        val selectDepartment = Select(department)
        selectDepartment.selectByIndex(0)// "ALL | Все"
        val document = driver.findElement(By.xpath("//input[contains(@data-bind, 'externalDocumentNumber')]"))
        document.sendKeys("TestDocSelenium${(1..100).random()}")
        val serialNumber = driver.findElement(By.xpath("//input[contains(@data-bind, 'moduleSerialNumber')]"))
        serialNumber.sendKeys(serialNum)
        val quantity = driver.findElement(By.xpath("//input[contains(@data-bind, 'modulesQuantity')]"))
        quantity.clear()
        quantity.sendKeys("2")
        val unit = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: measurementUnit')]"))
        val selectUnit = Select(unit)
        selectUnit.selectByIndex(1)
        val returnedOn = driver.findElement(By.xpath("//input[contains(@data-bind, 'moduleReturnedOn')]"))
        returnedOn.click()
        val selectData = driver.findElement(By.xpath("//a[text()= '15']"))
        selectData.click()
        val warrantyState = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: warrantyState')]"))
        val selectWarrantyState = Select(warrantyState)
        selectWarrantyState.selectByIndex(1)
        val selectFailures = driver.findElement(By.xpath("//a[@href= '#failures']"))
        selectFailures.click()
        val addFailures = driver.findElement(By.xpath("//span[text()= 'Add...']"))
        addFailures.click()
        Thread.sleep(10000)
        val selectOk = driver.findElement(By.xpath("//div[@class='title']/span[text()= 'Ок']"))
        selectOk.click()
        val nonconformity = driver.findElement(By.xpath("//textarea[@class= 'w100 h100 tooltip tooltipstered']"))
        nonconformity.sendKeys("TestSelenium")
    }

    private fun formMANClaimDecision() {
        val selectForm =
            driver.findElement(By.xpath("//div[@class='ui-widget-content slick-row even']/div[contains(@class, 'fa-caret-right selected')]"))
        selectForm.click()
        val selectDecision = driver.findElement(By.xpath("//div[@id='manDetails']//a[@href= '#decision']"))
        selectDecision.click()
        val returnedValuePolicy =
            driver.findElement(By.xpath("//select[contains(@data-bind, 'value: returnedValuePolicy')]"))
        val selectReturnedValuePolicy = Select(returnedValuePolicy)
        selectReturnedValuePolicy.selectByIndex(2)
        val comment = driver.findElement(By.xpath("//div[@class= 'shortcut']"))
        comment.click()
    }

    @Test(
        groups = ["CHS"], dependsOnMethods = ["createNewRuClaim"]
    )
    fun createNewMANClaim() {
        driverOptions()
        val selectMANClaim = driver.findElement(By.xpath("//span[text()= 'MANClaims']"))
        selectMANClaim.click()
        Thread.sleep(8000)
        val createMANClaim = driver.findElement(By.xpath("//div[@title= 'Создать Рекламацию Производителя']"))
        createMANClaim.click()
        formMANClaimCommon()
        val publishMANClaim = driver.findElement(By.xpath("//span[text()= 'Publish']"))
        publishMANClaim.click()
        Thread.sleep(5000)
        val closeFromCommon = driver.findElement(By.xpath("//button[@title='Close']"))
        closeFromCommon.click()
        formMANClaimDecision()
        val approveMANClaim = driver.findElement(By.xpath("//span[text()= 'Approve']"))
        approveMANClaim.click()
        Thread.sleep(5000)
        val closeFromDecision = driver.findElement(By.xpath("//button[@title='Close']"))
        closeFromDecision.click()
        driver.quit()
    }
}