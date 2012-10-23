(ns {{namespace}}.web.server
  (:use [ring.middleware.file :only [wrap-file]]
        [ring.middleware.file-info :only [wrap-file-info]]
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.nested-params :only [wrap-nested-params]]
        [ring.middleware.keyword-params :only [wrap-keyword-params]]
        [compojure.core :only [defroutes routes GET POST ANY]])
  (:require
    [ring.adapter.jetty :as jetty] :reload))

(defroutes base-handler
  (GET "/test" request (str request))

  (ANY "*" a "page not found"))



(defn start [& [port]]
  (jetty/run-jetty
   (-> #'base-handler
       (wrap-file "resources/public")
       wrap-file-info
       wrap-keyword-params
       wrap-nested-params
       wrap-params) {:port (or port 9990) :join? false}))

(defn stop [serv]
  (.stop serv))

(defn restart [serv]
  (stop serv)
  (.start serv))

(defn started? [serv]
  (or (.isRunning serv) (.isStarting serv) (.isStarted serv)))

(defn stopped? [serv]
  (or (.isStopped serv) (.isStopping serv) (.isFailed serv)))
