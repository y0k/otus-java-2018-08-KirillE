package ru.otus.l011;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Pullrequest trying
 * To start the application:
 * mvn package
 * java -cp ./target/L01.1-maven.jar ru.otus.l011.HelloOtus
 * java -jar ./target/L01.1-maven.jar //java.lang.NoClassDefFoundError: com/google/common/collect/Lists
 * java -cp ./target/L01.1-maven.jar;C:\Users\User\.m2\repository\com\google\guava\guava\25.0-jre\guava-25.0-jre.jar ru.otus.l011.HelloOtus
 *
 * To unzip the jar:
 * 7z x -oJAR ./target/L01.1-maven.jar
 * unzip -d JAR ./target/L01.1-maven.jar
 *
 * To build:
 * mvn package
 * mvn clean compile
 * mvn assembly:single
 * mvn clean compile assembly:single
 */
                               
public class HelloOtus {
    public static void main(String[] args) {
        String str = ",,,bam,  ,  bum,, bah   ,nah";
        
        String s = Joiner.on(", ").join(3, 2, 1);
        System.out.println(s);
        
       Iterable<String> p = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split(str);
       System.out.println(p);
    }
}
