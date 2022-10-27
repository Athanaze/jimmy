# Jimmy

An opinionated, minimalist MIPS assembly generator.

Compiles .jimmy files to .asm runnable on MIPS.

[VS code extension with basic syntax highlighting](https://github.com/linkeddata/webid-login)

# Syntax

## Comments

```
£ This is a comment. Comments must be on their own line (no inline comments)
```

## If

```
£ a less than 19 unsigned 
if(a <u 19)
```

## Initialization

``` 
a = 42
```

## Change value

``` 
a += 2
```

# Build from source

``` 
sbt package
```

# Roadmap
* [x] Single line comments
* [x] Initialization
* [x] Add register
* [x] Add immediate
* [ ] If statement (see note 1)
* [ ] direct translation to assembly labels
* [ ] for loop

# Notes

## 1

abstraction of the pattern

```
slt $t4, $t5, $zero # check the sign
bne $t4, $zero, skip #if positive goto skip
```