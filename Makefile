CC=javac

debug: src/Matriks.java src/Test.java
	$(CC) src/Test.java src/Matriks.java
	mv src/*.class test/

build: src/Main.java src/Matriks.java
	$(CC) src/Matriks.java src/Main.java
	mv src/*.class bin/

clean:
	rm test/*.class bin/*.class
