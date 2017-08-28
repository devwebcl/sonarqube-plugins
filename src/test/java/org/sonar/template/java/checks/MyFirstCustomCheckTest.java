package org.sonar.template.java.checks;

import org.junit.Test;

import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class MyFirstCustomCheckTest {

    @Test
    public void test() {
      //  it relies on usage of the JavaCheckVerifier class, provided by the Java Plugin rule testing API.
      //      <groupId>org.sonarsource.java</groupId>
      //  		<artifactId>java-checks-testkit</artifactId>
      JavaCheckVerifier.verify("src/test/files/MyFirstCustomCheck.java", new MyFirstCustomCheck());
    }

}

/*
    2.
    /src/test/java
    org.sonar.template.java.checks
    MyFirstCustomCheckTest.java
*/

