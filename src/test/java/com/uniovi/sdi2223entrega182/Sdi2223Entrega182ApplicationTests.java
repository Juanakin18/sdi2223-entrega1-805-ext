package com.uniovi.sdi2223entrega182;

import com.uniovi.sdi2223entrega182.pageobjects.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class Sdi2223Entrega182ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\Javier\\Desktop\\SDI\\LABORATORIOS\\sesion06\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";
    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }
    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }
    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }
    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}
    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    /**
     *  Registro de Usuario con datos válidos.
     */
    @Test
    @Order(1)
    void PR1() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        //Rellenamos el formulario.
        PO_SignUpView.fillForm(driver, "pepe@email.com", "Josefo", "Perez", "77777", "77777");
        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        String checkText = "Gestión de Ofertas";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    /**
     *  Registro de Usuario con datos inválidos.
     */
    @Test
    @Order(2)
    void PR2() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "alonso", "alonso", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.email.valid",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de email no valido
        String checkText = PO_HomeView.getP().getString("Error.signup.email.valid",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }
    /**
     *   Registro de Usuario con datos inválidos (repetición de contraseña inválida).
     */
    @Test
    @Order(3)
    void PR3() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "alonso@email.com", "alonso", "Perez", "1111", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.passwordConfirm.coincidence",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de contraseña mal repetida
        String checkText = PO_HomeView.getP().getString("Error.signup.passwordConfirm.coincidence",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }
    /**
     *  Registro de Usuario con datos inválidos (email existente).
     */
    @Test
    @Order(4)
    void PR4() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "mariobalotelli@uniovi.es", "alonso", "Perez", "1111", "1111");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.email.duplicate",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de email repetido
        String checkText = PO_HomeView.getP().getString("Error.signup.email.duplicate",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }
    /**
     *  Inicio de sesión con datos validos(admin)
     */
    @Test
    @Order(5)
    void PR5() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");
        String checkText = "Ofertas";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    /**
     *  Inicio de sesión con datos validos(usuario)
     */
    @Test
    @Order(6)
    void PR6() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        String checkText = "Lista Ofertas";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    /**
     *  Inicio de sesión con datos invalidos(usuario)
     */
    @Test
    @Order(7)
    void PR7() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "", "");
        String checkText = "Identifícate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    /**
     *  Inicio de sesión con datos invalidos(contraseña)
     */
    @Test
    @Order(8)
    void PR8() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "12s36");
        String checkText = "Credenciales erróneas o campos vacíos";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    /**
     *  Inicio de sesión con datos invalidos(contraseña)
     */
    @Test
    @Order(9)
    void PR9() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
        String checkText = "Identifícate";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }
    /**
     *  Inicio de sesión con datos invalidos(contraseña)
     */
    @Test
    @Order(10)
    void PR10() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "12s36");
        String checkText = "Credenciales erróneas o campos vacíos";
        assertThrows(Exception.class, () -> {PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");});

    }
    /**
     *  Añadir oferta con datos validos
     */
    @Test
    @Order(15)
    void PR15() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        //Cmmprobamos que entramos en la pagina privada del usuario
        PO_View.checkElementBy(driver, "text", "mariobalotelli@uniovi.es");
        //Pinchamos en la opción de menú de ofertas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "/html/body/nav/div/ul[1]/li[6]");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");
        //Pinchamos en agregar Nota.
        elements.get(0).click();

        //Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
        String checkText = "Oferta Nueva 1";
        PO_AddOfferView.fillForm(driver, checkText, "detalles de la oferta", "300");

        //Comprobamos que aparece la oferta en la página
        elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());
        //Ahora nos desconectamos y comprobamos que aparece el menú de registrarse
        PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

    }
    /**
     *  Añadir oferta con datos invalidos(precio)
     */
    @Test
    @Order(16)
    void PR16() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        //Cmmprobamos que entramos en la pagina privada del usuario
        PO_View.checkElementBy(driver, "text", "mariobalotelli@uniovi.es");
        //Pinchamos en la opción de menú de ofertas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "/html/body/nav/div/ul[1]/li[6]");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");
        //Pinchamos en agregar Nota.
        elements.get(0).click();

        //Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
        String checkText = "Oferta Nueva 1";
        PO_AddOfferView.fillForm(driver, checkText, "detalles de la oferta", "-1");

        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.addOffer.amount.domain",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de contraseña mal repetida
        checkText = PO_HomeView.getP().getString("Error.addOffer.amount.domain",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

    }
    /**
     *  Mostrar ofertas propias de un usuario
     */
    @Test
    @Order(17)
    void PR17() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        //Cmmprobamos que entramos en la pagina privada del usuario
        PO_View.checkElementBy(driver, "text", "mariobalotelli@uniovi.es");
        //Pinchamos en la opción de menú de ofertas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "/html/body/nav/div/ul[1]/li[6]");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        //Pinchamos en listar ofertas.
        elements.get(0).click();
        //Contamos el número de filas de ofertas
        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(4, markList.size());
        //Ahora nos desconectamos y comprobamos que aparece el menú de registro
        PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

    }
    /**
     *  Eliminar una oferta siendo el usuario que la creo
     */
    @Test
    @Order(18)
    void PR18() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        //Cmmprobamos que entramos en la pagina privada del usuario
        PO_View.checkElementBy(driver, "text", "mariobalotelli@uniovi.es");
        //Pinchamos en la opción de menú de ofertas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "/html/body/nav/div/ul[1]/li[6]");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        //Pinchamos en listar ofertas.
        elements.get(0).click();
        //Pinchamos en el enlace de borrado de la primera oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/delete')]");
        elements.get(0).click();
        //Contamos el número de filas de ofertas
        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(3, markList.size());
        //Ahora nos desconectamos y comprobamos que aparece el menú de registro
        PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
    }
    /**
     *  Eliminar la ultima oferta de la lista
     */
    @Test
    @Order(19)
    void PR19() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        //Cmmprobamos que entramos en la pagina privada del usuario
        PO_View.checkElementBy(driver, "text", "mariobalotelli@uniovi.es");
        //Pinchamos en la opción de menú de ofertas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "/html/body/nav/div/ul[1]/li[6]");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        //Pinchamos en listar ofertas.
        elements.get(0).click();
        //Pinchamos en el enlace de borrado de la ultima oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/delete')]");
        elements.get(elements.size()-1).click();
        //Contamos el número de filas de ofertas
        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(3, markList.size());
        //Ahora nos desconectamos y comprobamos que aparece el menú de registro
        PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
    }
    /**
     * Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el
     * sistema.
     */
    @Test
    @Order(11)
    void PR11(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");
        By enlace = By.xpath("//*[@id=\"listaUsuarios\"]");
        driver.findElement(enlace).click();
        String checkText = "Usuarios";
        PO_View.checkElementBy(driver,"text",checkText);
        //Contamos el número de filas de usuarios
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(7,userList.size() );
    }

    /**
     * Ir a la lista de usuarios, borrar el primer usuario de la lista, comprobar que la lista se actualiza
     * y dicho usuario desaparece.
     */
    @Test
    @Order(12)
    void PR12(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");
        By enlace2 = By.xpath("//*[@id=\"listaUsuarios\"]");
        driver.findElement(enlace2).click();
        String checkText = "Usuarios";
        PO_View.checkElementBy(driver,"text",checkText);
        //Contamos el número de filas de usuarios
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(7,userList.size() );
        By enlace = By.xpath("/html/body/div[1]/table/tbody/tr[1]/td[4]/input");
        driver.findElement(enlace).click();

        By enlaceBorrar = By.xpath("//*[@id=\"deleteAll\"]");
        //td[contains(text(), 'Nota A2')]/following-sibling::*[2]
        driver.findElement(enlaceBorrar).click();
        userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(6,userList.size() );


    }
    /**
     * Ir a la lista de usuarios, borrar el último usuario de la lista, comprobar que la lista se actualiza
     * y dicho usuario desaparece.
     */
    @Test
    @Order(13)
    void PR13(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");
        By enlace3 = By.xpath("//*[@id=\"listaUsuarios\"]");
        driver.findElement(enlace3).click();
        String checkText = "Usuarios";
        PO_View.checkElementBy(driver,"text",checkText);
        //Contamos el número de filas de usuarios
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(6,userList.size() );
        By enlace = By.xpath("/html/body/div[1]/table/tbody/tr[6]/td[4]/input");
        driver.findElement(enlace).click();
        By enlaceBorrar = By.xpath("//*[@id=\"deleteAll\"]");
        driver.findElement(enlaceBorrar).click();
        userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(5,userList.size() );
    }
    /**
     *  Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se actualiza y dichos
     * usuarios desaparecen.
     */
    @Test
    @Order(14)
    void PR14(){
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");
        By enlace = By.xpath("//*[@id=\"listaUsuarios\"]");
        driver.findElement(enlace).click();
        String checkText = "Usuarios";
        PO_View.checkElementBy(driver,"text",checkText);
        //Contamos el número de filas de usuarios
        List<WebElement> userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(5,userList.size() );
        By enlace1 = By.xpath("/html/body/div[1]/table/tbody/tr[1]/td[4]/input");
        driver.findElement(enlace1).click();
        By enlace2 = By.xpath("/html/body/div[1]/table/tbody/tr[2]/td[4]/input");
        driver.findElement(enlace2).click();
        By enlace3 = By.xpath("/html/body/div[1]/table/tbody/tr[3]/td[4]/input");
        driver.findElement(enlace3).click();
        By enlaceBorrar = By.xpath("//*[@id=\"deleteAll\"]");
        driver.findElement(enlaceBorrar).click();
        userList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(2,userList.size() );
    }
    /**
     *  Acceder sin estar autenticado a la opcion listado de usuarios
     */
    @Test
    @Order(30)
    void PR30() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "12s36");
        String checkText = "Credenciales erróneas o campos vacíos";
        assertThrows(Exception.class, () -> {PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");});

    }
    /**
     *  Crear una oferta con imagen adjunta
     */
    @Test
    @Order(40)
    void PR40() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        //Cmmprobamos que entramos en la pagina privada del usuario
        PO_View.checkElementBy(driver, "text", "mariobalotelli@uniovi.es");
        //Pinchamos en la opción de menú de ofertas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "/html/body/nav/div/ul[1]/li[6]");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");
        //Pinchamos en agregar Nota.
        elements.get(0).click();

        //Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
        String checkText = "Oferta Nueva 1";
        PO_AddOfferView.fillFormWithImage(driver, checkText, "detalles de la oferta", "300", "Descargas//prueba.png");

        //Comprobamos que aparece la oferta en la página
        elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());
        //Comprobamos que aparece la nueva imagen
        elements = PO_View.checkElementBy(driver, "free", "//img[contains(@alt, 'prueba.png')]");
        Assertions.assertTrue(elements.size()==5);
        //Ahora nos desconectamos y comprobamos que aparece el menú de registrarse
        PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
    }
    /**
     *  Crear una oferta sin imagen adjunta
     */
    @Test
    @Order(41)
    void PR41() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "mariobalotelli@uniovi.es", "123456");
        //Cmmprobamos que entramos en la pagina privada del usuario
        PO_View.checkElementBy(driver, "text", "mariobalotelli@uniovi.es");
        //Pinchamos en la opción de menú de ofertas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "/html/body/nav/div/ul[1]/li[6]");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");
        //Pinchamos en agregar Nota.
        elements.get(0).click();

        //Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
        String checkText = "Oferta Nueva 1";
        PO_AddOfferView.fillForm(driver, checkText, "detalles de la oferta", "300");

        //Comprobamos que aparece la oferta en la página
        elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());
        //Comprobamos que el numero de imagenes por defecto es 5
        elements = PO_View.checkElementBy(driver, "free", "//img[contains(@alt, 'default-image.png')]");
        Assertions.assertTrue(elements.size()==5);
        //Ahora nos desconectamos y comprobamos que aparece el menú de registrarse
        PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

}
