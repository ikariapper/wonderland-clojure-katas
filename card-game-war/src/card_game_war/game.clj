(ns card-game-war.game)

;; use base 13 number system to represent cards. first digit is rank, second digit is suit (spades = 0, clubs = 1, diamonds = 2, hearts = 3)
(defn compare-cards [cards]
  (let [ranks (into {} (for [[player card] cards] [player (mod card 13)]))
        suits (into {} (for [[player card] cards] [player (quot card 13)]))]
    (key (apply max-key val
      (cond
        (= (ranks :player1) (ranks :player2)) suits
        :else ranks)))))

(defn play-round [player-cards]
  (let [player1-card (first (player-cards :player1))
        player2-card (first (player-cards :player2))
        winner (compare-cards {:player1 player1-card :player2 player2-card})]
    (cond
      (= winner :player1) {:player1 (concat (rest (player-cards :player1)) [player1-card player2-card]) :player2 (rest (player-cards :player2))}
      (= winner :player2) {:player1 (rest (player-cards :player1)) :player2 (concat (rest (player-cards :player2)) [player2-card player1-card])})))

(defn play-game [player-cards]
  (loop [player-decks player-cards]
    (cond
      (empty? (player-decks :player1)) :player2
      (empty? (player-decks :player2)) :player1
      :else (recur (play-round player-decks)))))