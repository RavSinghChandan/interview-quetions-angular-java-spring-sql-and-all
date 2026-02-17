# GIT — LEVEL 3 (INTERMEDIATE)

**Stage Title: “You Enter the World of Real Teams”**

*(Story continues — now Git is no longer just your tool. It becomes the shared language between engineers.)*

---

## CHAPTER CONTEXT — WHAT CHANGES HERE

At Basic level you learned:

> how to use Git alone.

At Intermediate level you learn:

> how Git works in teams.

This is where most real-world complexity appears.

Because Git was not built for solo developers.

It was built for **collaboration at scale**.

---

# 1. THE FIRST TEAM REALITY

Working alone is simple.

Working with others introduces problems:

* someone edits same file
* someone pushes before you
* someone deletes code you need
* history diverges
* conflicts appear

Git exists to solve exactly these problems.

---

# 2. THE CENTRAL IDEA OF TEAM GIT

Each developer has:

```
their own copy
their own history
their own branches
```

No one works directly on main code.

Why?

Because:

> direct editing causes chaos.

So teams use **branch workflow**.

---

# 3. THE REAL TEAM WORKFLOW

Real developer cycle:

```
pull latest code
create branch
make changes
commit changes
push branch
create pull request
review
merge
```

This workflow prevents:

* broken builds
* overwritten code
* accidental deletions

---

# 4. WHY BRANCHES ARE EVERYTHING

Branch is not just feature tool.

Branch is:

> safety boundary.

Developers never work directly on main branch.

They create branch:

```
git checkout -b login-feature
```

Now they can experiment safely.

---

# 5. FETCH VS PULL — CRITICAL DIFFERENCE

Most beginners misuse these.

Fetch:

```
git fetch
```

Meaning:

> download updates but don’t change your code.

Pull:

```
git pull
```

Meaning:

> download + merge changes into your branch.

Professionals often fetch first, inspect, then merge.

---

# 6. MERGE CONFLICT — THE MOMENT EVERY DEV FACES

Conflict happens when:

Two people change same part of file.

Git cannot decide which version is correct.

So it stops and asks you.

Conflict markers look like:

```
<<<<<<< HEAD
your code
=======
their code
>>>>>>> branch
```

You manually fix file, then:

```
git add file
git commit
```

Conflict is not error.

Conflict means:

> Git is protecting code from being overwritten blindly.

---

# 7. FAST-FORWARD VS REAL MERGE

Sometimes merge is simple.

If history hasn’t diverged:

Git moves pointer forward.

This is called:

> fast-forward merge

If both branches changed:

Git creates merge commit.

That is:

> real merge.

---

# 8. REBASE — THE PROFESSIONAL TOOL

Merge combines histories.

Rebase rewrites history.

Rebase means:

> replay your commits on top of another branch.

Command:

```
git rebase main
```

Why use rebase?

Because it keeps history clean and linear.

Teams often prefer rebase for:

* clean logs
* readable history
* easier debugging

---

# 9. MERGE VS REBASE — REAL DIFFERENCE

Merge:

```
keeps history as-is
```

Rebase:

```
rewrites history cleanly
```

Rule professionals follow:

> Use rebase for local work
> Use merge for shared branches

---

# 10. DETACHED HEAD — SCARY BUT SIMPLE

Sometimes you checkout specific commit:

```
git checkout commit_id
```

Now HEAD points to commit, not branch.

This is called:

> detached HEAD state.

You’re exploring history, not editing branch.

---

# 11. STASH — TEMPORARY MEMORY

Sometimes you want to switch branch but have unfinished work.

Stash saves work temporarily:

```
git stash
```

Restore:

```
git stash pop
```

Stash is like:

> temporary drawer for unfinished changes.

---

# 12. TAGS — MARKING IMPORTANT MOMENTS

Tag marks specific commit.

Example:

```
git tag v1.0
```

Used for:

* releases
* versions
* deployments

---

# 13. RESET VS REVERT — VERY IMPORTANT

Reset:

```
git reset
```

Moves history pointer.

Revert:

```
git revert commitID
```

Creates new commit that undoes change.

Rule:

```
Reset → local undo
Revert → safe team undo
```

---

# 14. GIT HISTORY IS A GRAPH

By now you must understand:

Git history is not a list.

It is:

> a graph of commits.

View graph:

```
git log --graph --oneline --all
```

Understanding this graph is key to mastery.

---

# 15. REAL TEAM RULES

Professional teams follow rules:

* never commit directly to main
* small commits
* meaningful messages
* pull before push
* review before merge

Git discipline = engineering maturity.

---

# 16. REAL WORLD DEBUGGING USING GIT

When bug appears:

Teams don’t guess.

They check history:

```
git log
git diff
git blame
```

Git helps answer:

> who introduced bug and when?

---

# INTERMEDIATE LEVEL COMPLETION CHECK

You can now:

✔ collaborate safely
✔ resolve conflicts
✔ use branches correctly
✔ understand merge vs rebase
✔ stash work
✔ inspect history graph
✔ undo changes safely
✔ follow team workflows

---

# FINAL LINE FOR THIS CHAPTER

At this point:

> Git stops being a personal tool.

It becomes a collaboration system.

And engineers who understand collaboration systems…

are trusted with real production codebases.

---

END OF LEVEL 3 — INTERMEDIATE

---

📍 Next stage unlock:

Say
**PLUS**

Next chapter:

> “You begin understanding Git’s internal mechanics — how it really stores history.”
