(ns alphabet-cipher.coder)

(defn convertChar [char key]
  (clojure.core/char
    (+ 97
       (mod (+ (- (int char) 97)
               (- (int key) 97)) 26))))

(defn decodeChar [char key]
  (clojure.core/char
    (let [decoded
          (+ (- (int char) (int key)) 97)]
      (if (>= decoded 97) decoded (+ decoded 26)))))

(defn decipherChar [encoded decoded]
  (clojure.core/char
    (let [key
          (- (+ (int encoded) 97) (int decoded))]
      (if (>= key 97) key (+ key 26)))))

(defn decycle [str]
  (first
    (filter (fn [x] (.startsWith x (.replaceAll str x "")))
            (map (fn [i] (.substring str 0 (+ 1 i)))
                 (range (.length str))))))

(defn encode [keyword message]
  (apply str
         (map #(convertChar (nth % 0) (nth % 1))
              (map vector
                   (take (.length message) (cycle keyword)) message))))

(defn decode [keyword message]
  (apply str
         (map #(decodeChar (nth % 0) (nth % 1))
              (map vector message
                   (take (.length message) (cycle keyword))))))

(defn decipher [cypher message]
  (decycle (apply str
                  (map #(decipherChar (nth % 0) (nth % 1))
                       (map vector cypher message)))))

