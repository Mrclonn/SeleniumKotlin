package me.tum

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.ie.InternetExplorerOptions
import org.openqa.selenium.interactions.Actions
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.Duration

class IE {
    init {
        System.setProperty("webdriver.ie.driver", "src/main/kotlin/drivers/IEDriverServer.exe")
    }

    private val capabilities = InternetExplorerOptions().ignoreZoomSettings()
    private val driver = InternetExplorerDriver(capabilities)
    private val actions = Actions(driver)
//   private val select = new Select()

    private fun driverOptions() {
        driver.get(urlClassifierTest1)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60))
        actions.keyDown(Keys.CONTROL).sendKeys("0").keyUp(Keys.CONTROL).release().build().perform() // Zoom == 100%
    }

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
        val allNameType = driver.findElements(By.xpath("//div[@class= 'nodeContainer type']/span[3]"))
        val createType = driver.findElement(By.xpath("//div[@class= 'buttonWrapper']/input[@value= 'Create Type']"))
        createType.click()
        val inputValue =
            driver.findElement(By.xpath("//div[@class= 'dialog ui-dialog-content ui-widget-content']/div/table/tbody/tr/td/input[@type= 'text']"))
        // //*[@id="ui-id-1"]/div[1]/table/tbody/tr[1]/td[2]/input
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
        val nameNewType = "${nameTypeStr}${nameTypeNom.toInt().plus(1)}"
        inputValue.sendKeys(nameNewType)
        val buttonOK = driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div[2]/button[1]"))
        buttonOK.click()
        val targetNewType = driver.findElement(
            By.xpath(
                "/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[29]/ul/li[6]/ul/li[32]/ul/li[7]/ul/li[${
                    allNameType.size.plus(
                        1
                    )
                }]/div/span[3]"
            )
        )
        targetNewType.click()
        createParameters()
        driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[1]/table/tbody/tr[3]/td[3]"))
        val publishType =
            driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/div[5]/input[3]"))
        publishType.click()
        val approveType = driver.findElement(By.xpath("//*[@id=\"draftPublish\"]"))
        approveType.click()

        createVersion()
    }

    private fun createParameters() {
        val createParameter =
            driver.findElement((By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[2]/button")))
        createParameter.click()
        val valueParameter =
            driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[1]/table/tbody/tr[3]/td[1]/fieldset/div[1]/div[1]/div/div[2]/input"))
        valueParameter.sendKeys(nameParameter)
        val addValueFirst = driver.findElement(By.xpath("//*[@id=\"values\"]/div/button"))
        addValueFirst.click()
        val nameValueFirst = driver.findElement(By.xpath("//*[@id=\"values\"]/div[1]/div[1]/div[1]/div/div[2]/input"))
        nameValueFirst.sendKeys("1")
        val saveValueFirst = driver.findElement(By.xpath("//*[@id=\"values\"]/div[1]/div[2]/button[1]"))
        saveValueFirst.click()
        val trueValues = driver.findElements(By.xpath("//*[@id=\"values\"]/div[1]/table/tbody/tr/td[3]/span"))
        if (trueValues.isNotEmpty()) {
            var buttonAdd = 1
            while (countValue != 1) {
                countValue -= 1
                val addValue = driver.findElement(By.xpath("//*[@id=\"values\"]/div[${buttonAdd.plus(1)}]/button"))
                addValue.click()
                val nameValue =
                    driver.findElement(By.xpath("//*[@id=\"values\"]/div[${buttonAdd.plus(1)}]/div[1]/div[1]/div/div[2]/input"))
                nameValue.sendKeys("${trueValues.last().text.toInt().plus(buttonAdd)}")
                val saveValue =
                    driver.findElement(By.xpath("//*[@id=\"values\"]/div[${buttonAdd.plus(1)}]/div[2]/button[1]"))
                saveValue.click()
                buttonAdd += 1
            }
            val saveParameter =
                driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[1]/table/tbody/tr[3]/td[1]/fieldset/legend/span[1]"))
            saveParameter.click()
        }
    }


    fun createNewTypeWeb() {
        driver.get(urlWebClassifier)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60))
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

        val allNameType =
            driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[2]/ul/li[6]/ul/li[23]/ul/li[7]/ul/li/div/span[3]"))

        val createType = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div/div/input[1]"))
        createType.click()
        val inputValue = driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div[1]/table/tbody/tr[1]/td[2]/input"))
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
        val nameNewType = "${nameTypeStr}${nameTypeNom.toInt().plus(1)}"
        inputValue.sendKeys(nameNewType)
        val buttonOK = driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div[2]/button[1]"))
        buttonOK.click()
        val targetNewType = driver.findElement(
            By.xpath(
                "/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[2]/ul/li[6]/ul/li[23]/ul/li[7]/ul/li[${
                    allNameType.size.plus(
                        1
                    )
                }]/div/span[3]"
            )
        )
        targetNewType.click()

        createParameters()
        driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[1]/table/tbody/tr[3]/td[3]"))
        val publishType =
            driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/div[5]/input[3]"))
        publishType.click()
        val approveType = driver.findElement(By.xpath("//*[@id=\"draftPublish\"]"))
        approveType.click()
        createVersion()
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
                            driver.findElement(By.xpath("//span[@class= 'ui-icon ui-accordion-header-icon ui-icon-triangle-1-e']"))
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
            workCreationVersion()
        } else {
            val selectType = driver.findElement(By.xpath("//span[@class= 'name selected']"))
            selectType.click()
            workCreationVersion()
        }
    }


    fun createRevision(nameType: String = "") {
//        selectType(nameType)
        val nameRevision = driver.findElement(By.xpath(""))



    }
}