BT Phonetic Search program

by Thomas Bottjer

-----BUILDING THE PROGRAM IN WINDOWS-----

To build the Main program from the command line, first change the directory to the 
Phonetic_Search\src folder, using the command
"cd YOUR_LOCATION\Phonetic_Search\src"

For example if you downloaded the project folder to a location such as
C:\Documents\BT\Phonetic_Search
then change the directory using
"cd C:\Documents\BT\Phonetic_Search\src"

The next stage is to set the path to the JDK programs, this is done with the following command
"set path=%path%;YOUR_JAVA_INSTALL_LOCATION\JDK_VERSION\bin"

For example, if you have JDK 1.7 installed in you Program Files (x86) folder, your command may
look like:
"set path=%path%;C:\Program Files (x86)\Java\jdk1.7.0_02\bin"

You can now run the compiler to compile the Main.java file using the command
"javac com/bt/search/Main.java"

You can check that the class has been compiled by typing the command
"dir"

-----RUNNING THE PROGRAM-----

We can now run our program using the command
"java com.bt.seach.Main [Argumnets] < [input file]"

For example, to test the program with the given surnames.txt file in Phonetic_Search\src and
with the names Jones and Winton, we would use the command
"java com.bt.seach.Main Jones Winton < surnames.txt"

This gives the following output

Jones: Jonas, Johns, Saunas
Winton: Van Damme

Note: to use the plain file name as above, the file must be in the current working directory
i.e. YOUR_LOCATION\Phonetic_Search\src\

----------------------------------------------------------------------------------------------

References:
http://www.skylit.com/javamethods/faqs/javaindos.html