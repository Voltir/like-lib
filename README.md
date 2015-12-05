Like Library
============

This is a small library that makes it easier to use AnyVal style case classes (like UserId) around the "edges" of scala - ie during serialization, or for example, for use in working with play routes or in slick column mappings. This defines a series of related "Like" typeclasses that basically define to/from functions that allow conversions from the stronger AnyVal type to the wrapped primitive type. 
