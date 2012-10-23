(ns leiningen.new.blank-angular-website
  (:use [leiningen.newnew.templates :only [year project-name
                                        sanitize-ns name-to-path]]))

(defn blank-angular-website
  "A blank leiningen template for an angular website"
  [name]
  (println "Generating Angular Website")
  {:template true
   :data 
     {:raw-name name
      :name (project-name name)
      :namespace (sanitize-ns name)
      :nested-dirs (name-to-path name)
      :year (year)}
   :directives
     {:render-dirs [["" :except ["resources"]]]
      :copy-dirs [["resources"]]}})