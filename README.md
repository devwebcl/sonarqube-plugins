# sonarqube-plugins

Simple example based on the tutorial: [https://docs.sonarqube.org/display/PLUG/Writing+Custom+Java+Rules+101](https://docs.sonarqube.org/display/PLUG/Writing+Custom+Java+Rules+101)

This example demonstrates how to write **Custom Rules** for the SonarQube Java Analyzer (aka SonarJava).

It requires to install **SonarJava** **4.7.1.9272** on your SonarQube 5.6+

--> actually, it needs a newer version, please check pom.xml


| Class Filename| path           | Cool  |
| ------------- |-------------| -----|
| MyFirstCustomCheck.java| /src/test/files/|  A test file, which contains Java code used as input data for testing the rule |
| org.sonar.template.java.checks. MyFirstCustomCheckTest.java| /src/test/java |    A test class, which contains the rule's unit test |
| org.sonar.template.java.checks. MyFirstCustomCheck.java | /src/main/java|     A rule class, which contains the implementation of the rule. |



1.
```
import org.sonar.api.Plugin;

/**
 * Entry point of your plugin containing your custom rules
 */
public class MyJavaRulesPlugin implements Plugin {
```

This class is the entry point for the SONAR plugin. This class is extended from org.sonar.api.Plugin class. This class includes server extension which gets instanciated during sonarqube startup, and batch extensions which gets instantiated during the code analysis.


2.
```
/**
 * Declare rule metadata in server repository of rules.
 * That allows to list the rules in the page "Rules".
 */
 public class MyJavaRulesDefinition implements RulesDefinition {
```

This class is a Server extension which gets instanciated at the time of sonarqube startup. The repository name and supported language name is mentioned in this class

```
    // server extensions -> objects are instantiated during server startup
    context.addExtension(MyJavaRulesDefinition.class);

    // batch extensions -> objects are instantiated during code analysis
    context.addExtension(MyJavaFileCheckRegistrar.class);
```

3.
```
/**
 * Provide the "checks" (implementations of rules) classes that are going be executed during
 * source code analysis.
 *
 * This class is a batch extension by implementing the {@link org.sonar.plugins.java.api.CheckRegistrar} interface.
 */
@SonarLintSide
public class MyJavaFileCheckRegistrar implements CheckRegistrar {
```

This class is the batch extension which gets instanciated during the code analysis. This class registers all custom rule classes.


4.
```
/*Rule Activation

The second things to to is to activate the rule within the plugin. To do so, open class RulesList (org.sonar.samples.java.RulesList). In this class, you will notice methods GetJavaChecks() and GetJavaTestChecks(). These methods are used to register our rules with alongside the rule of the Java plugin.*/
public final class RulesList {
```

This class lists all custom rules and provides the list to the CustomJavaFileCheckRegistrar class to register them with sonarqube



