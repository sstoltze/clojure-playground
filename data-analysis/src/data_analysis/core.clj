(ns data-analysis.core
  (:gen-class)
  (:require [datomic.client.api :as d]
            [cheshire.core :as cc]))

(def client (d/client {:server-type :dev-local
                       :system "dev"
                       :storage-dir :mem}))

(d/create-database client {:db-name "folketings-data"})
(def conn (d/connect client {:db-name "folketings-data"}))


(defn cases-by-date
  [{:keys [year month day]
    :or {year false month false day false}}]
  (let [year-filter (format "year(opdateringsdato)%%20eq%%20%s" year)
        month-filter (format "month(opdateringsdato)%%20eq%%20%s" month)
        day-filter (format "year(opdateringsdato)%%20eq%%20%s" day)]
    (print year-filter)
    (format "https://oda.ft.dk/api/Sag?$inlinecount=allpages&$filter=%s" year-filter))
  )
(def sager (->
              slurp
              (cc/parse-string true)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
