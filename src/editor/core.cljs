(ns editor.core
  (:require [planck.shell :as sh]
            [planck.core :as core]

            [planck.repl :as repl]
            [clojure.string :as str]))

(def state (atom {}))

(defn make-buffer [file-contents]
  (str/split-lines file-contents))

(defn clear-screen []
  ((:raw-write *out*) (str "\033[2J" )))

(defn move-cursor [x y]
  ((:raw-write *out*) (str "\033[" (inc y) ";" (inc x) "H")))

(defn render [{:keys [buffer cursor]}]
  (clear-screen)
  (move-cursor 0 0)
  (doseq [line buffer]
    (print line "\r\n"))
  (move-cursor (:x cursor)
               (:y cursor)))

(defn move-down [state]
  (update-in state [:cursor :y ] inc))

(defn move-up [state]
  (update-in state [:cursor :y] dec))

(defn move-left [state]
  (update-in state [:cursor :x] dec))

(defn move-right [state]
  (update-in state [:cursor :x] inc))

(defn insert-at [line x ch]
  (let [[pre post] (split-at x line)]
    (str (apply str pre) ch (apply str post))))

(defn insert [{:keys [buffer cursor] :as state} ch]
  (let [{:keys [x y]} cursor
        current-line (nth buffer y)]
    
    (assoc-in state [:buffer y] (insert-at current-line x ch))))

(defn clamp-x [{:keys [buffer cursor] :as state}]
  (let [{:keys [x y] } cursor
        current-line-length (count (nth buffer y))]
    (cond
      (< x 0) (assoc-in state [:cursor :x] 0)
      (> x current-line-length) (assoc-in state [:cursor :x] current-line-length)
      :else state)))

(defn clamp-y [{:keys [buffer cursor] :as state}]
  (let [{:keys [x y]} cursor]
    (cond
      (< y 0) (assoc-in state [:cursor :y] 0)
      (< (count buffer) y) (assoc-in state [:cursor :y] (count buffer))
      :else state)))

(defn backspace [{:keys [buffer cursor] :as state}]
  (let [{:keys [x y]} cursor
        current-line (nth buffer y)
        [pre post] (split-at x current-line)]
    (assoc-in state [:buffer y] (str (apply str (butlast pre)) (apply str post)))))

(defn handle-input [{:keys [buffer cursor] :as state}]
  (let [ch ((:raw-read core/*in*))]
    (condp = ch
      "" (core/exit 0)
      "" (clamp-y (move-down state))
      "" (clamp-y (move-up state))
      "" (clamp-x (move-left state))
      "" (clamp-x (move-right state))
      "" ((comp move-left backspace) state)
      ((comp move-right insert) state ch))))

(defn editor [file-contents]

  (reset! state {:buffer (make-buffer file-contents)
                 :cursor {:x 0 :y 0}})
  (loop []
    (render @state)
    (swap! state handle-input)
    (recur)))

(defn -main [file]
  (editor (core/slurp file)))

