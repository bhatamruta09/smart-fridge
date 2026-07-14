# Smart Fridge — Project Plan & Roadmap

Daily time budget assumed: ~2 hours/day (project) + 30 min/day (job applications, separate)

---

## Phase 1: Planning & Requirements ✅ DONE
**Time taken:** ~1 hour (completed)
- Problem statement, user stories, MVP scope, non-functional requirements
- Output: Initial README.md

---

## Phase 2: System Design & Architecture
**Estimated time: 2–3 hours (1–2 sessions)**

What we'll do:
1. **Architecture diagram** — how Angular frontend, Spring Boot backend, PostgreSQL database, and the external AI API connect (simple boxes-and-arrows diagram, I'll help you build this)
2. **Database schema design** — define `User` and `Ingredient` tables, their fields, and the relationship between them (one user → many ingredients)
3. **API design** — list out every REST endpoint before coding: method, path, request body, response
   - e.g. `POST /api/auth/register`, `GET /api/ingredients`, `POST /api/ingredients/suggest-recipe`
4. **Tech stack justification** — short written rationale for each technology choice (useful interview prep material)

Output: `architecture.md` in a `docs/` folder + diagram image + updated README architecture section

---

## Phase 3: Project Setup & Repo Foundation
**Estimated time: 1 hour**

What we'll do:
1. Create GitHub repo, push initial structure + README
2. Root-level `.gitignore`
3. Confirm both backend and frontend run cleanly (sanity check, like we did before)
4. Set up basic Docker Compose skeleton (Postgres service, placeholders for backend/frontend)

Output: Clean repo on GitHub with working skeleton, first few commits

---

## Phase 4: Implementation (Iterative — biggest phase)
**Estimated time: 10–14 hours total (5–7 sessions of ~2 hours)**

Broken into sub-steps, each following: design → code → test → document

| Step | What | Est. Time |
|---|---|---|
| 4a | Ingredient entity, repository, service, controller (backend CRUD) | 2 hrs |
| 4b | Angular ingredient components (list, add, edit, delete) + service to call backend | 2.5–3 hrs |
| 4c | JWT authentication — backend (User entity, register/login, security config, filter) | 3 hrs |
| 4d | Frontend auth (login/register pages, interceptor, route guards) | 2 hrs |
| 4e | AI recipe suggestion feature (backend AI service call + frontend "What can I cook?" button/UI) | 2.5–3 hrs |

*We'll test each piece as we finish it, not all at the end — so "testing" time is partially folded into each step above, with dedicated deeper testing in Phase 5.*

---

## Phase 5: Testing
**Estimated time: 3–4 hours**

What we'll do:
1. **Unit tests (JUnit + Mockito)** — service layer logic (e.g. does the AI prompt get built correctly, does ingredient validation work)
2. **Integration tests** — controller → service → repository flow, using an in-memory test database (H2)
3. Basic frontend tests if time allows (Angular's built-in testing tools)

Output: A `src/test` folder with meaningful coverage, not just placeholder tests

---

## Phase 6: CI/CD & Deployment
**Estimated time: 3–5 hours (this phase often has surprises — budget extra)**

What we'll do:
1. GitHub Actions workflow — runs tests + builds Docker images on every push (covering **both** frontend and backend this time)
2. Deploy backend + database (options: Render, Railway, or Fly.io — all have free tiers good for a portfolio project)
3. Deploy frontend (Vercel or Netlify — free, fast for Angular)
4. Connect them, test the live version end-to-end

Output: A live URL you can share in applications and interviews

---

## Phase 7: Documentation & Polish
**Estimated time: 1–2 hours**

What we'll do:
1. Finalize README — screenshots, live demo link, setup instructions
2. Clean up code comments where needed
3. Final review pass — does everything work end-to-end from a stranger's perspective (i.e., could a recruiter clone and run this, or at least understand it from the README alone)

---

## Total estimated time: ~22–30 hours

At 2 hours/day, that's roughly **3–4 weeks** of steady daily work to a fully deployed, tested, documented portfolio project — matching your existing daily rhythm (30 min job apps + 2 hrs build).

---

## How we'll work each day going forward
1. Start of session: quick recap of where we left off (I'll always re-check the repo/code state, not assume)
2. Confirm the day's specific goal (e.g. "today: Ingredient entity + repository")
3. Build with explanation at each step — nothing added without understanding why
4. End of session: note what's done, what's next, commit to GitHub

---

*Last updated: Phase 1 complete, starting Phase 2/3 next session*
