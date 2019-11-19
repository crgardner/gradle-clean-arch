# Persistence
This module implements the database gateway interface adapter in the Clean Architecture.
It implements the gateway interfaces and hides the Spring Data JPA framework.  This
prevents other modules from knowing that Spring Data is used and provides the freedom to
change the implementation as necessary.

Instead of mandating the use of annotations in the entities, or domain, module, it uses
orm.xml and requires each entity to be mapped in that file.  This again provides the freedom
to implement persistence any way we need and prevents the use case interactors and entities
layers of the Clean Architecture from having any visible dependencies on a particular
persistence mapping framework.

As many developers are used to annotating entities, using xml is rare, and it is hard to find
good references.  For a good mapping xml mapping guide, we rely on the online book [Java
Persistence](https://en.wikibooks.org/wiki/Java_Persistence).

