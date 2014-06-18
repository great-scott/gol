(ns gol.monome-test
  (:require [clojure.test :refer :all]
            [gol.monome :refer :all]))

(def monome-info {:args (list "m000016" "monome 256" 44100)})

(deftest device-config-sets-port
  (testing "device config..."
    (device-config monome-info)
    (is (= APP_PORT 44100))))
