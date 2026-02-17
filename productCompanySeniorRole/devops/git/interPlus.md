# GIT — LEVEL 4 (INTERMEDIATE+)

**Stage Title: “You See Inside Git’s Brain”**

*(Story continues — now you stop using Git as a tool and start understanding how it actually works internally.)*

---

## CHAPTER CONTEXT — WHAT CHANGES HERE

At Intermediate level you learned:

> how teams use Git.

At this level you learn:

> how Git itself works.

This is where most developers stop.

But engineers who go further unlock a powerful advantage:

They stop memorizing commands…
because they understand the system behind them.

---

# 1. THE BIG REALIZATION — GIT IS A DATABASE

Git is not a command tool.

Git is:

> a content-addressable database.

Meaning:

Git stores data based on **content hash**, not filename.

Every object Git stores has a unique ID.

Example hash:

```
e3b0c44298fc1c149afbf4c8996fb924
```

That hash identifies content forever.

---

# 2. WHAT GIT ACTUALLY STORES

Git stores only four object types:

```
Blob
Tree
Commit
Tag
```

Understanding these is key to mastering Git.

---

## Blob — file content

Blob = raw file data.

Git does NOT store filename here.

Just content.

---

## Tree — folder structure

Tree stores:

* filenames
* permissions
* directory structure

---

## Commit — snapshot record

Commit stores:

* tree pointer
* parent commit
* author
* message
* timestamp

---

## Tag — label

Tag marks specific commit.

Usually used for releases.

---

# 3. COMMITS ARE LINKED GRAPHS

Each commit stores reference to previous commit:

```
Commit C → Commit B → Commit A
```

This creates chain.

Branches simply point to latest commit.

---

# 4. BRANCHES ARE JUST POINTERS

This is a huge realization.

Branch is NOT copy of code.

Branch is just:

> pointer to commit.

Example:

```
main → commit A
feature → commit B
```

Switch branch → move pointer.

That’s all.

Branch creation is instant because Git isn’t copying files.

It’s moving pointers.

---

# 5. HEAD — THE CURRENT POINTER

HEAD is special pointer.

It tells Git:

> which branch you are currently on.

Example:

```
HEAD → main → commit A
```

When you commit:

```
HEAD moves forward
```

So commits don’t move.
Pointers move.

---

# 6. WHY COMMITS ARE FAST

Other systems store file differences.

Git stores snapshots using hashing.

But it is efficient because:

If file unchanged → Git reuses previous blob.

So Git saves space automatically.

---

# 7. STAGING AREA — THE SECRET ENGINE

Most developers don’t understand staging.

Staging area is:

> a preparation zone for next commit.

It lets you choose exactly what goes into snapshot.

Meaning:

You can edit 10 files
but commit only 2.

This is why Git history can be clean.

---

# 8. DETACHED HEAD EXPLAINED CLEARLY

Detached HEAD means:

HEAD points directly to commit, not branch.

```
HEAD → commit A
```

Not:

```
HEAD → main → commit A
```

You can explore past safely.

But commits made here can be lost unless you create branch.

---

# 9. REBASE INTERNAL LOGIC

Rebase doesn’t merge histories.

It:

> replays commits one by one onto another base.

So if history is:

```
A — B — C (main)
     \
      D — E (feature)
```

After rebase:

```
A — B — C — D' — E'
```

Git rewrites D and E as new commits.

That’s why rebase changes commit IDs.

---

# 10. MERGE INTERNAL LOGIC

Merge creates new commit with two parents.

```
     D — E
    /
A — B — C
        \
         M
```

Merge commit M links both histories.

That’s why merges preserve true history.

---

# 11. WHY HASHES MATTER

Git uses SHA hashing.

Hash depends on content.

If file changes → hash changes.

This guarantees:

> integrity

No one can secretly change history without changing hashes.

---

# 12. WHY GIT IS FAST

Because Git works locally.

Most commands operate on local database.

Network is only used when:

```
push
pull
fetch
```

Everything else is instant.

---

# 13. HOW GIT DETECTS CHANGES

Git doesn’t track files.

Git tracks content hashes.

So it detects changes by comparing hashes, not timestamps.

That’s why Git is reliable.

---

# 14. WHY YOU SOMETIMES GET “MERGE CONFLICT”

Conflict happens when two commits modify same content hash area.

Git cannot automatically decide correct version.

So it asks human.

Conflict means:

> history branches disagree.

---

# 15. GIT HISTORY IS IMMUTABLE

Commits cannot be changed.

They can only be:

* replaced
* abandoned
* rewritten

But original commit always exists until garbage collected.

---

# 16. GARBAGE COLLECTION

Git cleans unused data using:

```
git gc
```

This removes unreachable objects.

Git is basically managing its own database storage.

---

# 17. WHY UNDERSTANDING THIS LEVEL CHANGES EVERYTHING

Most developers memorize commands.

Engineers who understand internals can:

* recover lost commits
* fix broken history
* debug repository corruption
* repair mistakes safely
* optimize workflows

This is what interviewers actually test.

---

# INTERMEDIATE+ COMPLETION CHECK

You now understand:

✔ Git stores snapshots not files
✔ branches are pointers
✔ commits form graph
✔ HEAD is pointer
✔ merge vs rebase internally
✔ why hashes guarantee integrity
✔ why Git is fast
✔ staging purpose

---

# FINAL LINE FOR THIS CHAPTER

At this point:

> Git stops being magic.

It becomes predictable.

And predictable systems are controllable systems.

---

END OF LEVEL 4 — INTERMEDIATE+

---

📍 Next stage unlock:

Say
**ADVANCED**

Next chapter:

> “You learn how elite engineers debug Git problems no one else can solve.”
