application.properties - файл конфигурации задает параметры для подключения к базе данных MySQL, настройки Hibernate и
Thymeleaf, а также уровня логирования для Spring Security.

Настройки Datasource
spring.datasource.url: Указывает URL для подключения к базе данных MySQL, расположенной на локальном хосте,
                       с именем базы данных restaurant. Параметр createDatabaseIfNotExist=true создает базу данных,
                       если она еще не существует.
spring.datasource.username: Имя пользователя для доступа к базе данных (rest).
spring.datasource.password: Пароль для доступа к базе данных (rest).
spring.datasource.driver-class-name: Указывает класс драйвера JDBC для MySQL (com.mysql.cj.jdbc.Driver).
spring.datasource.initialization-mode: Определяет, будет ли инициализация базы данных выполняться всегда (always),
                                       что означает, что Spring будет автоматически выполнять скрипты для
                                       инициализации базы данных при запуске приложения.

Настройки Hibernate
spring.jpa.hibernate.ddl-auto: Значение update означает, что Hibernate будет автоматически обновлять схему базы данных
                               на основе изменений в моделях JPA.
spring.jpa.show-sql: Включение отображения SQL-запросов в консоли (true), что полезно для отладки.
spring.jpa.properties.hibernate.dialect: Указывает диалект Hibernate для MySQL 8 (org.hibernate.dialect.MySQL8Dialect),
                                      который обеспечивает корректное преобразование операций SQL для данной базы данных.

Настройки Thymeleaf
spring.thymeleaf.enabled: Включение поддержки Thymeleaf в приложении (true).
spring.thymeleaf.prefix: Указывает путь к шаблонам Thymeleaf (класс classpath:/templates/).
spring.thymeleaf.suffix: Указывает суффикс для файлов шаблонов (.html).
spring.thymeleaf.mode: Устанавливает режим обработки шаблонов в HTML5 (HTML5).
spring.thymeleaf.encoding: Устанавливает кодировку для шаблонов (UTF-8).
spring.thymeleaf.cache: Отключение кеширования шаблонов (false), что удобно при разработке, так как позволяет
                        видеть изменения сразу после их внесения.

Настройки логирования
logging.level.org.springframework.security: Устанавливает уровень логирования для компонента Spring Security на DEBUG,
                             что позволяет получать подробные сообщения для отладки вопросов, связанных с безопасностью.









