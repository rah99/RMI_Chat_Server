# RMI_Chat_Server
An encryption server using RMI (Remote Method Invocation) clients connect through an interface, to do so the client must have the specific RMI Service name and by default connects via port 1099. In this type of service, the interface passes the data between the two programs (server and client programs). The presented program makes use of a Server, Interface, and Client where the client makes an instance of the Interface to communicate with the Server. The Server and Client utilize serialVersionUID that ensures the classes used to start the program finish it. The Server registers a service name to the RMI registry of the host system, this name must be used by the client to connect. Encryption was carried out using the Jasypt library, which is an API for java encryption, it works by adding a key (cipher key) to the data to send and then encrypting the combination using a cryptographic algorithm, which can only be decrypted by the receiver using the same cipher key and cryptographic algorithm. The produced program allows a user to login to the Server through a username and password and allows the user to send a message that is echoed back as confirmation of reception. **Note* the encryption is only used for the password to show that the service still works without encryption but that the concept of encryption is understood and could have been applied to all.

## Using RMI Program

### NOTE - Was built using Mavern in Eclipse (all dependencies in the pom.xml)
```
Start the RMI Server
```
```
Start the RMI Client
```
### User logins
#### username 1
```
user1 
```
#### password username 1
```
1234
```
#### username 2
```
user2
```
#### password username 2
```
1234
```
#### Enter message at the prompt

## Resources and acknowledgements

* https://www.youtube.com/watch?v=St6xad6z1DI
* https://stackoverflow.com/questions/20176040/why-set-a-password-using-jasypt-when-encrypting-text
* https://blog.jayway.com/2008/12/09/encrypting-properties-with-jasypt/
* http://www.jasypt.org/api/jasypt/1.9.2/org/jasypt/encryption/pbe/StandardPBEStringEncryptor.html
* https://www.programcreek.com/java-api-examples/?api=org.jasypt.encryption.pbe.StandardPBEStringEncryptor
* http://www.jasypt.org/api/jasypt/1.8/org/jasypt/util/password/rfc2307/RFC2307MD5PasswordEncryptor.html#RFC2307MD5PasswordEncryptor()
* https://stackoverflow.com/questions/939111/change-default-rmi-port-java
* https://www.javatpoint.com/RMI
* https://stackoverflow.com/questions/7303266/difference-between-salt-and-key-encryption
* https://easycodestuff.blogspot.com/2014/11/rmi-chat-program-using-java.html
* https://security.stackexchange.com/questions/147255/should-failed-login-attempts-be-logged
* https://stackoverflow.com/questions/4631088/rmi-create-thread-on-server-to-serve-client
* https://www.javatpoint.com/java-treemap
* https://www.tutorialspoint.com/java/java_set_interface.htm
