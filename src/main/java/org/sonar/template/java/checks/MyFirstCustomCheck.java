package org.sonar.template.java.checks;

import com.google.common.collect.ImmutableList;

import org.sonar.check.Rule;

import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import java.util.List;

@Rule(key = "MyFirstCustomRule")
public class MyFirstCustomCheck extends IssuableSubscriptionVisitor {

  @Override
  public List<Kind> nodesToVisit() {
    //return ImmutableList.of(); // primer ejemplo vacio

    //visitamos metodos
    //org.sonar.plugins.java.api.tree.MethodTree (the interface tree associated with the METHOD kind).
    return ImmutableList.of(Kind.METHOD);
  }

  // we have to implement how the rule will react when encountering method declarations.
  @Override
  public void visitNode(Tree tree) {

      MethodTree method = (MethodTree) tree;

      // rule by checking that the method has a single parameter, and raise an issue if it's the case.
      if (method.parameters().size() == 1) {
            reportIssue(method.simpleName(), "Never do that!");
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
