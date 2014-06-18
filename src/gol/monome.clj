(ns gol.monome
  (:use [overtone.osc]))

(def SERIAL_OSC_PORT 12002)
(def LISTEN 44100)

; define a client
(def serialosc-client (osc-client "localhost" SERIAL_OSC_PORT))
(def app-server (osc-server LISTEN))

; callback from device query
(defn device-config [msg]
  (def APP_PORT (last (get msg :args)))
  (def gol-client (osc-client "localhost" APP_PORT)))

(osc-handle app-server "/serialosc/device" device-config)

(defn device-info []
  (osc-send serialosc-client "/serialosc/list" "localhost" LISTEN))

(defn sys-info [client]
  (osc-send client "/sys/info" "localhost" APP_PORT))

(defn send-sys-info [client prefix rotation]
  (osc-send client "/sys/port" APP_PORT)
  (osc-send client "/sys/prefix" prefix)
  (osc-send client "/sys/rotation" rotation))
