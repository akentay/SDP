# SDP
📘 Assignment 1 – Divide and Conquer Algorithms
🎯 Learning Goals

Implement classic divide-and-conquer algorithms with safe recursion patterns.

Analyze running-time recurrences using Master Theorem and Akra–Bazzi intuition, and validate with measurements.

Collect metrics (execution time, recursion depth, comparisons/allocations).

Communicate results via report and maintain a clean Git history.

⚙️ Implemented Algorithms
1. MergeSort (D&C, Master Case 2)

Uses a linear-time merge procedure.

Reuses a buffer to reduce allocations.

Switches to Insertion Sort for small subproblems (n < cutoff).

Recurrence:

𝑇
(
𝑛
)
=
2
𝑇
(
𝑛
/
2
)
+
Θ
(
𝑛
)
⇒
𝑇
(
𝑛
)
=
Θ
(
𝑛
log
⁡
𝑛
)
T(n)=2T(n/2)+Θ(n)⇒T(n)=Θ(nlogn)
2. QuickSort (Robust)

Randomized pivot selection for good expected balance.

Always recurses on the smaller partition, iterates over the larger one (to keep stack depth bounded).

Typical recursion depth ≈ O(log n).

Recurrence (expected case):

𝑇
(
𝑛
)
=
𝑇
(
𝑛
/
2
)
+
Θ
(
𝑛
)
⇒
Θ
(
𝑛
log
⁡
𝑛
)
T(n)=T(n/2)+Θ(n)⇒Θ(nlogn)
3. Deterministic Select (Median-of-Medians, O(n))

Groups elements in blocks of 5, finds median of medians.

Uses pivot for in-place partition.

Only recurses into the needed half.

Recurrence:

𝑇
(
𝑛
)
=
𝑇
(
𝑛
/
5
)
+
𝑇
(
7
𝑛
/
10
)
+
Θ
(
𝑛
)
⇒
Θ
(
𝑛
)
T(n)=T(n/5)+T(7n/10)+Θ(n)⇒Θ(n)
4. Closest Pair of Points (2D, O(n log n))

Sorts points by x-coordinate.

Recursive split by halves.

Checks the middle strip in O(n) time using y-sorting and scanning up to 7–8 neighbors.

Recurrence:

𝑇
(
𝑛
)
=
2
𝑇
(
𝑛
/
2
)
+
Θ
(
𝑛
)
⇒
Θ
(
𝑛
log
⁡
𝑛
)
T(n)=2T(n/2)+Θ(n)⇒Θ(nlogn)
🧪 Metrics

Execution Time (measured with System.nanoTime()).

Recursion Depth (tracked by a global counter).

Comparisons / Allocations (tracked via MetricsCollector).

CSV Export: results are written for plotting.

📊 Results & Analysis

Time vs n: MergeSort and QuickSort scale as Θ(n log n), while Select is linear Θ(n).

Depth vs n: QuickSort depth remains bounded near 2*log₂(n).

Constant factors: Insertion sort cutoff improves MergeSort for small n; cache effects are visible at large n.

📌 Summary

Theoretical predictions match measurements closely.

QuickSort with randomized pivot performs better in practice than MergeSort for many cases, though MergeSort guarantees worst-case Θ(n log n).

Deterministic Select is slower in practice than randomized QuickSelect but provides worst-case O(n) guarantee.

Closest Pair demonstrates the power of divide-and-conquer in geometry.

🛠️ Project Workflow

Branches:

main: only working releases.

feature/mergesort, feature/quicksort, feature/select, feature/closest, feature/metrics.

Commit Storyline followed as per assignment:

init: maven, junit5, ci, readme

feat(metrics): counters, depth tracker, CSV writer

feat(mergesort): baseline + reuse buffer + cutoff + tests

feat(quicksort): smaller-first recursion, randomized pivot + tests

feat(select): deterministic select (MoM5) + tests

feat(closest): divide-and-conquer implementation + tests

feat(cli): parse args, run algos, emit CSV

bench(jmh): harness for select vs sort

docs(report): master cases & AB intuition, plots

fix: edge cases

release: v1.0

✅ Testing

Sorting verified on random and adversarial arrays.

QuickSort recursion depth checked ≲ 2*floor(log₂n) + O(1).

Select compared with Arrays.sort(a)[k] across 100 trials.

Closest Pair validated against O(n²) brute-force for n ≤ 2000.
