# EPAMTASK.XML.XSD.GREEN_HOUSE
first attempt sax, dom parser working fine
Task. XML\XSD
создат ХМL-Файл, храняший информации об обектах определенной предметной области.Для валидации полученого ХМL-Файл реобходиьо разработать 
соответствующую ему схему XSD. выполнить парсирг ХМL-документа с использованет DOM, SAX и/или STAX, JAXB парсеров.

1 - оранжерея (GreenHouse)
растения, содержащиеся и оранжерея
Name:
Soil:
Origin:
VisualParameter:
GrowingTips:
Reproduction:

steps to create the project.
1 - creating the plant class.
2 - extending from it another 2 classes, give to each of the child class some variable to make it differ than the supper class.
3 - create the VisualParameter class and GrwoingTips class.
4 - create enum class that will hold name of the variable and value of the variable. such as NAME("name")
5 - creating xsd schema first and write inside it all the condtition that you will use to c=validate a xml file aginst it.
6 - create xml file depends on the xsd file validator
7 - create java class to class to validate a xml file against xsd file incase if some one send you an unkown file so before parsing it
    you need to check it.
8 - create interface parser that will hold a method to parse the xml file such lie that <Plant>plantList(String xmlFile)
9 - implement that class then start to create the parser that will fit your needs.
