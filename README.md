Refer

https://docs.spring.io/spring-hateoas/docs/current/reference/html/

for new changes in hateoas

Representation models
The ResourceSupport/Resource/Resources/PagedResources group of classes never really felt appropriately named. After all, these types do not actually manifest resources but rather representation models that can be enriched with hypermedia information and affordances. Here’s how new names map to the old ones:

ResourceSupport is now RepresentationModel

Resource is now EntityModel

Resources is now CollectionModel

PagedResources is now PagedModel

ControllerLinkBuilder is now WebMvcLinkBuilder

----------------------------------------------------------

Swagger changes refer swagger migration docs

http://springfox.github.io/springfox/docs/current/#introduction

https://springdoc.org/migrating-from-springfox.html

example 
	1) https://dzone.com/articles/simplified-spring-swagger
	2) https://dzone.com/articles/openapi-3-documentation-with-spring-boot
Used code from example 2

----------------------------------------------

For actuator refer the below link


https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html#production-ready-endpoints


You can make use of basic endpoint just by adding actuator dependency. For additional ones add the necessary properties in application.properities
