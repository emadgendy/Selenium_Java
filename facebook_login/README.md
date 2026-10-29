# Facebook Automation

This repository is a Maven multi-module project. Its automation tests are in
`AutomateFaceBook`.

Run the full test suite from the repository root:

```shell
mvn test
```

Run only the Facebook login regression test from the repository root:

```shell
mvn -pl AutomateFaceBook -Dtest=RegressionTest#faceBookLogin test
```