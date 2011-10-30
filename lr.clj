(ns lr)

(defn sq [x] (* x x))

(defn r [acc v]
  {:x (+ (acc :x) (v :x))
   :y (+ (acc :y) (v :y))
   :xy (+ (acc :xy) (* (v :x) (v :y)))
   :x2 (+ (acc :x2) (* (v :x) (v :x)))})

(defn lr [m] (let [init-acc {:x 0 :y 0 :xy 0 :x2 0}
		   s (reduce r init-acc m)
		   c (count m)
		   w1 (/ (- (* c (s :xy)) (* (s :x) (s :y))) (- (* c (s :x2)) (sq (s :x)) ) )
		   w0 (- (/ (s :y) c) (/ (* w1 (s :x)) c))]
	       
	       {:w0 w0 :w1 w1}))

(lr [{:x 2 :y 2}
     {:x 4 :y 5}
     {:x 6 :y 5}
     {:x 8 :y 8}])