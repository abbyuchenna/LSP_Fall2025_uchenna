
#reflection of Assignment 2 vs Assignment 3
## What changed in the design
**A2** was a single procedural class (`ETLPipeline`) with static helpers.  
**A3** decomposes the pipeline into classes with clear responsibilities:

- `CsvExtractor` – file I/O + parsing
- `ProductTransformer` – all business rules in order
- `CsvLoader` – output writing
- `ProductRaw` / `ProductTransformed` – explicit data models
- `PriceRange` – enum for classification
- `ETLPipelineApp` – orchestration and summary
- `Counters` – bookkeeping shared across layers

This separation makes responsibilities explicit and testable in isolatin

## How A3 is more object-oriented
- **Objects & Classes:** Each responsibility lives in its own class; data has types (`ProductRaw`, `ProductTransformed`).
- **Encapsulation:** Parsing/IO details are hidden inside `CsvExtractor`/`CsvLoader`; callers just pass `Path` and receive models.
- **Polymorphism (potential):** The design allows swapping `ProductTransformer` with another implementation, or creating an interface if requirements grow.
- **Cohesion & Coupling:** Classes are cohesive; coupling is via small, typed interfaces (lists of models, not raw strings).

## OO concepts used
- **Encapsulation:** internal parsing/rounding/IO details are not exposed.
- **Abstraction:** the pipeline is expressed as `extract → transform → load` objects.
- **(Optional) Polymorphism:** could introduce a `Transformer<T, R>` interface; the code is structured to allow that later.
- **Composition:** `ETLPipelineApp` composes the three stages.

## Verifying A3 equals A2
- Reused the same `data/products.csv`.
- Compared `data/transformed_products.csv` from A2 vs A3 (diff matched on my test set).
- Tested edge cases:
  - **Missing input:** A3 prints a clear error and exits.
  - **Empty input:** A3 writes header-only output.

## Notes / Assumptions
- CSV has no embedded commas/quotes (per spec).
- Rounding uses `BigDecimal` with `HALF_UP` to 2 decimals.
- Transform order is enforced exactly as in A2.