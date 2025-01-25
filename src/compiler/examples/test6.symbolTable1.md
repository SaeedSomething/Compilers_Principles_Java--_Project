# PROGRAM_program

+++++++++++++++++++++++++++++++++++++++++++
## program_Shape : ClassSymbol{name='Shape', isAbstract=true, isInterface=false, isMain=false, isImported=false, parentClass='Object', line=69, col=0, scope=program}|
## program_Circle : ClassSymbol{name='Circle', isAbstract=false, isInterface=false, isMain=false, isImported=false, parentClass='Shape', line=46, col=0, scope=program}|
## program_Main : ClassSymbol{name='Main', isAbstract=false, isInterface=false, isMain=true, isImported=false, parentClass='Object', line=2, col=0, scope=program}|
## program_Circle1 : ClassSymbol{name='Circle1', isAbstract=false, isInterface=false, isMain=false, isImported=false, parentClass='Object', line=78, col=0, scope=program}|
## program_intf : ClassSymbol{name='intf', isAbstract=false, isInterface=true, isMain=false, isImported=false, parentClass='Object', line=118, col=0, scope=program}|

+++++++++++++++++++++++++++++++++++++++++++

# CLASS_Main

+++++++++++++++++++++++++++++++++++++++++++
## Main_main : MethodSymbol{name='main', access modifier= public, isAbstract=false, isInterface=false, isMain=true, isConstructor=false, overrides=false, returnType='void', paramType='[MethodParamSymbol{name='args', line=2, col=0, type='array of String'}]', line=2, col=0, scope=Main}|

+++++++++++++++++++++++++++++++++++++++++++

# METHOD_Main

+++++++++++++++++++++++++++++++++++++++++++
## Main_args : MethodParamSymbol{name='args', line=2, col=0, type='array of String'}|
## Main_calc : LocalVarSymbol{name='calc', line=37, accessModifier=public, col=8, type=Calculator, isInitialized=true, value='newCalculator()'}|
## Main_rectangle : LocalVarSymbol{name='rectangle', line=7, accessModifier=public, col=8, type=Shape, isInitialized=true, value='newRectangle(4,6)'}|
## Main_circle : LocalVarSymbol{name='circle', line=6, accessModifier=public, col=8, type=Shape, isInitialized=false, value='null'}|
## Main_numbers : LocalVarSymbol{name='numbers', line=18, accessModifier=public, col=8, type=array of int, isInitialized=true, value='newint[5]'}|
## Main_sum : LocalVarSymbol{name='sum', line=26, accessModifier=public, col=8, type=int, isInitialized=true, value='0'}|
## Main_result : LocalVarSymbol{name='result', line=38, accessModifier=public, col=8, type=int, isInitialized=true, value='calc.multiply(10,20)'}|
## Main_i : LocalVarSymbol{name='i', line=27, accessModifier=public, col=13, type=int, isInitialized=true, value='0'}|

+++++++++++++++++++++++++++++++++++++++++++

# BLOCK_for

+++++++++++++++++++++++++++++++++++++++++++
## for_ff : LocalVarSymbol{name='ff', line=29, accessModifier=public, col=12, type=null, isInitialized=true, value='5'}|
## for_sum : LocalVarSymbol{name='sum', line=30, accessModifier=public, col=12, type=null, isInitialized=true, value='sum+numbers[i]'}|

+++++++++++++++++++++++++++++++++++++++++++

# CLASS_Circle

+++++++++++++++++++++++++++++++++++++++++++
## Circle_Circle : MethodSymbol{name='Circle', access modifier= public, isAbstract=false, isInterface=false, isMain=false, isConstructor=true, overrides=false, returnType='void', paramType='[MethodParamSymbol{name='radius', line=52, col=4, type='int'}]', line=52, col=4, scope=Circle}|
## Circle_chos : LocalVarSymbol{name='chos', line=48, accessModifier=public, col=4, type=int, isInitialized=false, value='null'}|
## Circle_area : MethodSymbol{name='area', access modifier= public, isAbstract=false, isInterface=false, isMain=false, isConstructor=false, overrides=true, returnType='int', paramType='void', line=59, col=4, scope=Circle}|
## Circle_radius : LocalVarSymbol{name='radius', line=47, accessModifier=public, col=4, type=int, isInitialized=false, value='null'}|

+++++++++++++++++++++++++++++++++++++++++++

# METHOD_Circle

+++++++++++++++++++++++++++++++++++++++++++
## Circle_radius : LocalVarSymbol{name='radius', line=53, accessModifier=public, col=8, type=int, isInitialized=true, value='radius'}|

+++++++++++++++++++++++++++++++++++++++++++

# METHOD_area

+++++++++++++++++++++++++++++++++++++++++++
## area_3 : LocalVarSymbol{name='3', line=64, accessModifier=public, col=16, type=null, isInitialized=false, value='null'}|
## area_radius : LocalVarSymbol{name='radius', line=61, accessModifier=public, col=8, type=int, isInitialized=true, value='3'}|

+++++++++++++++++++++++++++++++++++++++++++

# CLASS_Shape

+++++++++++++++++++++++++++++++++++++++++++
## Shape_area : MethodSymbol{name='area', access modifier= public, isAbstract=true, isInterface=false, isMain=false, isConstructor=false, overrides=false, returnType='int', paramType='void', line=74, col=4, scope=Shape}|
## Shape_x : LocalVarSymbol{name='x', line=70, accessModifier=private, col=4, type=int, isInitialized=false, value='null'}|
## Shape_y : LocalVarSymbol{name='y', line=71, accessModifier=public, col=4, type=int, isInitialized=false, value='null'}|

+++++++++++++++++++++++++++++++++++++++++++

# METHOD_area

+++++++++++++++++++++++++++++++++++++++++++
No symbols in this table
+++++++++++++++++++++++++++++++++++++++++++

# CLASS_Circle1

+++++++++++++++++++++++++++++++++++++++++++
## Circle1_multiply : MethodSymbol{name='multiply', access modifier= public, isAbstract=false, isInterface=false, isMain=false, isConstructor=false, overrides=false, returnType='int', paramType='[MethodParamSymbol{name='a', line=86, col=4, type='int'}, MethodParamSymbol{name='b', line=86, col=4, type='int'}]', line=86, col=4, scope=Circle1}|
## Circle1_add : MethodSymbol{name='add', access modifier= private, isAbstract=false, isInterface=false, isMain=false, isConstructor=false, overrides=false, returnType='int', paramType='[MethodParamSymbol{name='a', line=81, col=4, type='int'}, MethodParamSymbol{name='b', line=81, col=4, type='int'}]', line=81, col=4, scope=Circle1}|

+++++++++++++++++++++++++++++++++++++++++++

# METHOD_add

+++++++++++++++++++++++++++++++++++++++++++
## add_b : MethodParamSymbol{name='b', line=81, col=4, type='int'}|
## add_a : MethodParamSymbol{name='a', line=81, col=4, type='int'}|
## add_a+b : LocalVarSymbol{name='a+b', line=82, accessModifier=public, col=20, type=null, isInitialized=false, value='null'}|

+++++++++++++++++++++++++++++++++++++++++++

# METHOD_multiply

+++++++++++++++++++++++++++++++++++++++++++
## multiply_b : MethodParamSymbol{name='b', line=86, col=4, type='int'}|
## multiply_a : LocalVarSymbol{name='a', line=88, accessModifier=public, col=8, type=int, isInitialized=false, value='null'}|
## multiply_a*b : LocalVarSymbol{name='a*b', line=96, accessModifier=public, col=20, type=null, isInitialized=false, value='null'}|

+++++++++++++++++++++++++++++++++++++++++++

# BLOCK_if

+++++++++++++++++++++++++++++++++++++++++++
## if_iy : LocalVarSymbol{name='iy', line=90, accessModifier=public, col=8, type=int, isInitialized=true, value='this.add(2,5)'}|

+++++++++++++++++++++++++++++++++++++++++++

# BLOCK_else

+++++++++++++++++++++++++++++++++++++++++++
No symbols in this table
+++++++++++++++++++++++++++++++++++++++++++

# CLASS_intf

+++++++++++++++++++++++++++++++++++++++++++
## intf_sote : MethodSymbol{name='sote', access modifier= public, isAbstract=false, isInterface=true, isMain=false, isConstructor=false, overrides=false, returnType='int[]', paramType='[MethodParamSymbol{name='asd', line=120, col=4, type='array of int'}]', line=120, col=4, scope=intf}|
## intf_r : LocalVarSymbol{name='r', line=119, accessModifier=public, col=4, type=int, isInitialized=true, value='5'}|

+++++++++++++++++++++++++++++++++++++++++++

# METHOD_sote

+++++++++++++++++++++++++++++++++++++++++++
## sote_asd : MethodParamSymbol{name='asd', line=120, col=4, type='array of int'}|

+++++++++++++++++++++++++++++++++++++++++++

