package me.tum

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import org.testng.annotations.Test
import java.time.Duration

class IeClassifier {
    init {
        System.setProperty("webdriver.ie.driver", "src/main/kotlin/drivers/IEDriverServer.exe")
    }

    private val capabilities = InternetExplorerOptions().ignoreZoomSettings()
    private val driver = InternetExplorerDriver(capabilities)
    private val actions = Actions(driver)


    private fun driverOptions() {
        driver.get(urlClassifierTest1)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30))
        actions.keyDown(Keys.CONTROL).sendKeys("0").keyUp(Keys.CONTROL).release().build().perform() // Zoom == 100%
    }

    @Test (groups = ["Classifier"])
    fun createNewType() {
        driverOptions()
        val selectCG =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='CG']/parent::div/span[2]"))
        selectCG.click()
        val selectTestZone =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Test-Zone']/parent::div/span[2]"))
        selectTestZone.click()
        val selectProduct =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Products']/parent::div/span[2]"))
        selectProduct.click()
        val selectGroup =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Test2']/parent::div/span[2]"))
        selectGroup.click()
        val allSubgroup = driver.findElements(By.xpath("//div[@class= 'nodeContainer subgroup']"))
        val selectSubgroup =
            driver.findElement(By.xpath("//div[@class= 'nodeContainer subgroup']/span[text()='${allSubgroup.last().text}']/parent::div/span[2]"))
        selectSubgroup.click()
        allSubgroup.last().click()
        val allNameType = driver.findElements(By.xpath("//div[contains(@class, 'nodeContainer type')]/span[3]"))
        var nameNewType = ""
        if (allNameType.isEmpty()) {
            nameNewType = "1"
        } else {
            var nameTypeNom = ""
            var nameTypeStr = ""
            allNameType.last().text.forEach {
                if (it.isDigit()) {
                    nameTypeNom += it
                }
                if (it.isLetter()) {
                    nameTypeStr += it
                }
            }
            nameNewType = "${nameTypeStr}${nameTypeNom.toInt().plus(1)}"
        }
        val createType = driver.findElement(By.xpath("//div[@class= 'buttonWrapper']/input[@value= 'Create Type']"))
        createType.click()
        val inputValue =
            driver.findElement(By.xpath("//div[@class= 'dialog ui-dialog-content ui-widget-content']/div/table/tbody/tr/td/input[@type= 'text']"))
        inputValue.sendKeys(nameNewType)
        val buttonOK = driver.findElement(By.xpath("//div[@class= 'buttons']/button[text()= 'Ok']"))
        buttonOK.click()
        val targetNewType =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer type')]/span[text() = '${nameNewType}']"))
        targetNewType.click()
        createParameters()
        driver.findElement(By.xpath("//td[text()= '${nameParameter}']"))
        val publishType = driver.findElement(By.xpath("//div[@class= 'rightButtons']/input[@value = 'Publish']"))
        publishType.click()
        val approveType = driver.findElement(By.xpath("//div[@class= 'rightButtons']/input[@value = 'Approve']"))
        approveType.click()
        createVersion()
        createRevision()
        driver.quit()
    }

    private fun createParameters() {
        val createParameter = driver.findElement((By.xpath("//legend[text()= 'Configurator']/../div/button")))
        createParameter.click()
        val valueParameter = driver.findElement(By.xpath("//div[@class= 'tdinput']/input[@title='Parameter Code']"))
        valueParameter.sendKeys(nameParameter)
        val descriptionParameter =
            driver.findElement(By.xpath("//div[@class= 'tdinput']/textarea[@title='Parameter Description']"))
        descriptionParameter.sendKeys(nameParameter)
        val addValueFirst = driver.findElement(By.xpath("//div/button[text()= 'Add value']"))
        addValueFirst.click()
        val nameValueFirst = driver.findElement(By.xpath("//div[@class= 'tdinput']/input[@title= 'Value Code']"))
        nameValueFirst.sendKeys("1")
        val descriptionValueFirst =
            driver.findElement(By.xpath("//div[@class= 'tdinput']/textarea[@title= 'Value Description']"))
        descriptionValueFirst.sendKeys("test description 1")
        val saveValueFirst = driver.findElement(By.xpath("//div[@class='right-text']/button[text()= 'Save']"))
        saveValueFirst.click()
        val trueValues =
            driver.findElements(By.xpath("//td[contains(@data-bind, 'isForParametricAssemblyType')]/span[@class= 'command']"))
        if (trueValues.isNotEmpty()) {
            while (countValue != 1) {
                countValue -= 1
                val addValue = driver.findElement(By.xpath("//div/button[text()= 'Add value']"))
                addValue.click()
                val nameValue = driver.findElement(By.xpath("//div[@class= 'tdinput']/input[@title= 'Value Code']"))
                val descriptionValue =
                    driver.findElement(By.xpath("//div[@class= 'tdinput']/textarea[@title= 'Value Description']"))
                val allNameValues =
                    driver.findElements(By.xpath("//td[contains(@data-bind, 'isForParametricAssemblyType')]/span[@class= 'command']"))
                nameValue.sendKeys("${allNameValues.last().text.toInt().plus(1)}")
                descriptionValue.sendKeys("test description ${allNameValues.last().text.toInt().plus(1)}")
                val saveValue = driver.findElement(By.xpath("//div[@class='right-text']/button[text()= 'Save']"))
                saveValue.click()
            }
            val saveParameter = driver.findElement(By.xpath("//span[@class= 'imageButton save']"))
            saveParameter.click()
        }
    }

    fun createNewTypeWeb() {
        driver.get(urlWebClassifier)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30))
        actions.keyDown(Keys.CONTROL).sendKeys("0").keyUp(Keys.CONTROL).release().build().perform()
        val selectCG =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='CG']/parent::div/span[2]"))
        selectCG.click()
        val selectTestZone =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Test-Zone']/parent::div/span[2]"))
        selectTestZone.click()
        val selectProduct =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Products']/parent::div/span[2]"))
        selectProduct.click()
        val selectGroup =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Test2']/parent::div/span[2]"))
        selectGroup.click()
        val allSubgroup = driver.findElements(By.xpath("//div[@class= 'nodeContainer subgroup']"))
        val selectSubgroup =
            driver.findElement(By.xpath("//div[@class= 'nodeContainer subgroup']/span[text()='${allSubgroup.last().text}']/parent::div/span[2]"))
        selectSubgroup.click()
        allSubgroup.last().click()
        val allNameType = driver.findElements(By.xpath("//div[contains(@class, 'nodeContainer type')]/span[3]"))
        var nameNewType = ""
        if (allNameType.isEmpty()) {
            nameNewType = "1"
        } else {
            var nameTypeNom = ""
            var nameTypeStr = ""
            allNameType.last().text.forEach {
                if (it.isDigit()) {
                    nameTypeNom += it
                }
                if (it.isLetter()) {
                    nameTypeStr += it
                }
            }
            nameNewType = "${nameTypeStr}${nameTypeNom.toInt().plus(1)}"
        }
        val createType = driver.findElement(By.xpath("//div[@class= 'buttonWrapper']/input[@value= 'Create Type']"))
        createType.click()
        val inputValue =
            driver.findElement(By.xpath("//div[@class= 'dialog ui-dialog-content ui-widget-content']/div/table/tbody/tr/td/input[@type= 'text']"))
        inputValue.sendKeys(nameNewType)
        val buttonOK = driver.findElement(By.xpath("//div[@class= 'buttons']/button[text()= 'Ok']"))
        buttonOK.click()
        val targetNewType =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer type')]/span[text() = '${nameNewType}']"))
        targetNewType.click()
        createParameters()
        driver.findElement(By.xpath("//td[text()= '${nameParameter}']"))
        val publishType = driver.findElement(By.xpath("//div[@class= 'rightButtons']/input[@value = 'Publish']"))
        publishType.click()
        val approveType = driver.findElement(By.xpath("//div[@class= 'rightButtons']/input[@value = 'Approve']"))
        approveType.click()
        createVersion()
        createRevision()
    }

    private fun deleteNewType() {
        val deleteTestType = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div/div/input[2]"))
        Thread.sleep(8000)
        deleteTestType.click()
        val buttonDeleteType = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/button[1]"))
        buttonDeleteType.click()
        driver.quit()
    }

    private fun selectType(nameType: String) {
        driverOptions()
        val selectCG =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='CG']/parent::div/span[2]"))
        selectCG.click()
        val selectTestZone =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Test-Zone']/parent::div/span[2]"))
        selectTestZone.click()
        val selectProduct =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Products']/parent::div/span[2]"))
        selectProduct.click()
        val selectGroup =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer')]/span[text()='Test2']/parent::div/span[2]"))
        selectGroup.click()
//        val nameSubgroup = "s777"
//        val selectSpecificSubgroup =
//            driver.findElement(By.xpath("//div[@class= 'nodeContainer subgroup']/span[text()='${nameSubgroup}']/parent::div/span[2]"))
//        selectSpecificSubgroup.click()
        val allSubgroup = driver.findElements(By.xpath("//div[@class= 'nodeContainer subgroup']"))
        val selectSubgroup =
            driver.findElement(By.xpath("//div[@class= 'nodeContainer subgroup']/span[text()='${allSubgroup.last().text}']/parent::div/span[2]"))
        selectSubgroup.click()
        val selectType =
            driver.findElement(By.xpath("//div[contains(@class, 'nodeContainer type')]/span[text()='${nameType}']"))
        selectType.click()
    }

    private fun workCreationVersion() {
        val selectListValue = driver.findElement(By.xpath("//div[@class= 'dropDownOverlay']"))
        selectListValue.click()

        // Выбор свободного значения для создания Версии
        var exit = ""
        var i = 1
        while (exit != "Create") {
            val allValue =
                driver.findElements(By.xpath("//div[@class= 'customDropDown']/table/tbody/tr/td[@data-bind= 'text: value']"))
            allValue[i].click()
            val allButton = driver.findElements(By.xpath("//th[@class= 'buttonCell']/button"))
            allButton.forEach {
                when (it.text) {
                    "Create" -> {
                        val createButtonVersion =
                            driver.findElement(By.xpath("//th[@class= 'buttonCell']/button[text()='Create']"))
                        createButtonVersion.click()
                        val selectOk =
                            driver.findElement(By.xpath("//span[@data-bind= 'loading: \$parent.parent.savingVersion']/input[@value = 'Ok']"))
                        selectOk.click()
                        Thread.sleep(3000)
                        val blockVersion =
                            driver.findElement(By.xpath("//div[@class= 'VersionGridContainer']/table/tbody/tr/td/span[contains(@class, 'ui-icon')]"))
                        //span[@class= 'ui-icon ui-accordion-header-icon ui-icon-triangle-1-e']
                        blockVersion.click()
                        exit = "Create"
                    }
                    "Apply" -> {
                        i += 1
                        selectListValue.click()
                    }
                    "Request" -> {
                        i += 1
                        selectListValue.click()
                    }
                }
            }
        }
        // Согласование Версии
        val publishVersion = driver.findElement(By.xpath("//div[@class= 'rightButtons']/input[@value= 'Publish']"))
        publishVersion.click()
        val approveVersion = driver.findElement(By.xpath("//div[@class= 'rightButtons']/input[@value= 'Approve']"))
        approveVersion.click()
    }

    fun createVersion(nameType: String = "") {
        if (nameType != "") {
            selectType(nameType)
        }
//        else {
//            val selectType = driver.findElement(By.xpath("//span[@class= 'name selected']"))
//            selectType.click()
//        }
        Thread.sleep(8000)
        workCreationVersion()
    }

    private fun workCreationRevision() {
        val allNameRevision = driver.findElements(By.xpath("//a[@data-bind='text: manufacturingId']"))
        if (allNameRevision.size == 1) {
            val blockRevision =
                driver.findElement(By.xpath("//legend[text() = 'Revisions']/../table/tbody/tr[contains(@data-bind,'selected')]/td/span[contains(@class, 'ui-icon')]"))
            blockRevision.click()
            signDefault()
        } else {
            val blockRevisions =
                driver.findElements(By.xpath("//legend[text() = 'Revisions']/../table/tbody/tr[contains(@data-bind,'selected')]/td/span[contains(@class, 'ui-icon')]"))
            blockRevisions.last().click()
            signDefault()
            val scope = driver.findElement(By.xpath("//textarea[contains(@data-bind, 'target: scope')]"))
            scope.sendKeys("Test scope revision")
            val reason = driver.findElement(By.xpath("//textarea[contains(@data-bind, 'target: reason')]"))
            reason.sendKeys("Test reason revision")
        }
        val selectBomSource = driver.findElement(By.xpath("//select[contains(@data-bind, 'value: bomSource')]"))
        val selectExcel = Select(selectBomSource)
        selectExcel.selectByValue("2")
        val sendFile = driver.findElement(By.xpath("//input[@type='file']"))
        sendFile.sendKeys("C:\\Users\\tev\\IdeaProjects\\SeleniumKotlin\\src\\main\\kotlin\\Files\\FileAddBom.xlsx")
        val uploadFile = driver.findElement(By.xpath("//input[@type='submit']"))
        uploadFile.click()
        val bomOk =
            driver.findElement(By.xpath("//div[@class= 'dialog ui-dialog-content ui-widget-content']/div[@class= 'buttons']/button[text()= 'Ok']"))
        bomOk.click()
        val publishRevision = driver.findElement(By.xpath("//div[@class= 'rightButtons']/input[@value= 'Publish']"))
        publishRevision.click()
        val approveRevision = driver.findElement(By.xpath("//div[@class= 'rightButtons']/input[@value= 'Approve']"))
        approveRevision.click()
    }

    private fun signDefault() {
        val default = driver.findElement(By.xpath("//input[contains(@data-bind, 'checked: isDefaultRevision')]"))
        if (!default.isSelected) {
            default.click()
        }
    }

    fun createRevision(nameType: String = "") {
        if (nameType != "") {
            selectType(nameType)
            val selectLastVersion =
                driver.findElements(By.xpath("//a[contains(@data-bind, 'attr: {title: viewValue')]"))
            selectLastVersion.last().click()
        }
        Thread.sleep(3000)
        val createRevision =
            driver.findElement(By.xpath("//legend[text()= 'Revisions']/../div[@class='headLine']/table/tbody/tr/td/input"))
        createRevision.click()
        val createRevisionOk =
            driver.findElement(By.xpath("//span[@data-bind='loading: savingRevision']/input[@value= 'Ok']"))
        createRevisionOk.click()
        Thread.sleep(5000)
        val allNameRevision = driver.findElements(By.xpath("//a[@data-bind='text: manufacturingId']"))
        val nameRevision = allNameRevision.last().text
        WorkDoc().workExcel(nameRevision)
        workCreationRevision()
    }
}
