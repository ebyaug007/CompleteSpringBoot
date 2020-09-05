Refer

https://docs.spring.io/spring-hateoas/docs/current/reference/html/

for new changes in hateoas

Representation models
The ResourceSupport/Resource/Resources/PagedResources group of classes never really felt appropriately named. After all, these types do not actually manifest resources but rather representation models that can be enriched with hypermedia information and affordances. Here’s how new names map to the old ones:

ResourceSupport is now RepresentationModel

Resource is now EntityModel

Resources is now CollectionModel

PagedResources is now PagedModel