(ns card-game-war.game)

;; cards represented by 0-51. 0-12=2,3,4...,J,Q,K,A
;; spades = 0-12, clubs = 13-25, diamonds = 26-38, hearts = 39-51

;; TODO: take in map of player1->card to clean things up
(defn compare-cards [player1-card player2-card]
  (let [ranks (map #(-> (mod % 13)) [player1-card player2-card])
        suits (map #(-> (quot % 13)) [player1-card player2-card])]
    (cond
      (> (nth ranks 0) (nth ranks 1)) "player1"
      (< (nth ranks 0) (nth ranks 1)) "player2"
      (> (nth suits 0) (nth suits 1)) "player1"
      (< (nth suits 0) (nth suits 1)) "player2")))

(defn play-round [player1-cards player2-cards]
  (let [[player1-card & player1-deck] player1-cards
        [player2-card & player2-deck] player2-cards
        winner (compare-cards player1-card player2-card)]
    (cond
      (= winner "player1") [(conj player1-deck player1-card player2-card) player2-deck]
      (= winner "player2") [player1-deck (conj player2-deck player2-card player1-card)])))

(defn play-game [player1-cards player2-cards]
  (cond
    (empty? player1-cards) "player2"
    (empty? player2-cards) "player1"
    :else (apply play-game (play-round player1-cards player2-cards))))
