package org.sonar.template.java.checks;

import com.google.common.collect.ImmutableList;

import org.sonar.check.Rule;
import org.sonar.check.Priority;

import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.semantic.Type;

import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.samples.java.checks.PrinterVisitor;

import java.util.List;

/*
 * Sacado del tutorial !
 * https://docs.sonarqube.org/display/PLUG/Writing+Custom+Java+Rules+101#WritingCustomJavaRules101-Threefilestoforgearule
 *
 */

//@Rule(key = "MyFirstCustomRule")
@Rule(
  key = "MyFirstCustomCheck",
  name = "Return type and parameter of a method should not be the same",
  description = "For a method having a single parameter, the types of its return value and its parameter should never be the same.",
  priority = Priority.CRITICAL,
  tags = {"bug"} )
public class MyFirstCustomCheck extends IssuableSubscriptionVisitor {

    /*
     * Now its finally time to jump in to the implementation of our first rule! Go back to the MyFirstCustomCheck class,
     * and modify the list of Kinds returned by the nodesToVisit() method. Since our rule targets method declarations,
     * we only need to visit methods. To do so, simply add Kind.METHOD as a parameter of the returned immutable list,
     */
  @Override
  public List<Kind> nodesToVisit() {
    //return ImmutableList.of(); // primer ejemplo vacio

      // For debugging purpose, you can print out the entire AST of the analyzed file
        //System.out.println(PrinterVisitor.print(context.getTree()));

    //visitamos metodos
    //org.sonar.plugins.java.api.tree.MethodTree (the interface tree associated with the METHOD kind).
    return ImmutableList.of(Kind.METHOD);
  }

  /*
   * Once the nodes to visit are specified, we have to implement how the rule will react when encountering method declarations.
   * To do so, override method visitNode(Tree tree), inherited from SubscriptionVisitor through IssuableSubscriptionVisitor.
   */
  // we have to implement how the rule will react when encountering method declarations.
  @Override
  public void visitNode(Tree tree) {

      MethodTree method = (MethodTree) tree;

      // rule by checking that the method has a single parameter, and raise an issue if it's the case.
      if (method.parameters().size() == 1) {

          MethodSymbol symbol = method.symbol();  //semantic
          Type firstParameterType = symbol.parameterTypes().get(0); //parametro entrada
          Type returnType = symbol.returnType().type(); // tipo de salida

          //Since the rule should only raise an issue when these two types are the same, we then simply test if the return type is the same as the type of the first parameter using method is(String fullyQualifiedName), provided through the Type class, before raising the issue.
          if (returnType.is(firstParameterType.fullyQualifiedName())) {
              reportIssue(method.simpleName(), "Never do that!");
          }

          //legacy:
          //reportIssue(method.simpleName(), "Never do that!");
          }
  }

}

/*
    3.
    /src/main/java
    org.sonar.template.java.checks
    MyFirstCustomCheck.java

    (tiene impl, usa guava)
*/
