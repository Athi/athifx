#!/bin/bash

mvn clean package && cd athi-fx-test/target && java --module-path "$PATH_TO_FX" --add-modules=javafx.controls -jar AthiFXTest.jar && cd ..\.. || exit