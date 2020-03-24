you just need to 
```Java
import static [package.name].IntoText.digitsToText;
```
and pass the number as a parameter
```Java
for (int i = 0; i < 1000000; i++) 
    {
   numbers[i] = digitsToText(i);
    }
```
