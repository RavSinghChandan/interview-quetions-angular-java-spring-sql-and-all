# GIT — LEVEL 5 (ADVANCED)

**Stage Title: “You Become the Person Who Fixes Broken Repositories”**

*(Story continues — this is where you move from strong Git user to real Git engineer.)*

---

## CHAPTER CONTEXT — WHAT CHANGES HERE

At Intermediate+ level you learned:

> how Git works internally.

At Advanced level you learn:

> how to diagnose and repair Git problems.

This is rare knowledge.

Most developers know Git usage.
Very few understand Git recovery.

This level teaches you how professionals handle:

* broken histories
* lost commits
* bad merges
* corrupted states
* accidental mistakes

---

# 1. THE REALITY OF PRODUCTION GIT PROBLEMS

Real-world Git problems are not:

> “How do I commit?”

They are:

* I lost my commit
* wrong branch pushed
* history messed up
* merge broke repo
* rebase destroyed history
* HEAD detached and changes gone

Advanced engineers don’t panic.

They investigate.

---

# 2. FINDING LOST COMMITS

Git almost never deletes commits immediately.

Even if you reset or delete branch, commit still exists.

Find them using:

```
git reflog
```

Reflog shows:

> every HEAD movement.

Recover commit:

```
git checkout commitID
```

Then save it:

```
git checkout -b recovered
```

You just restored lost work.

---

# 3. UNDERSTANDING REFLOG — SECRET LIFE SAVER

Reflog records timeline like:

```
HEAD@{0}
HEAD@{1}
HEAD@{2}
```

You can return to previous state:

```
git checkout HEAD@{1}
```

This is why experts say:

> Almost nothing is lost in Git.

---

# 4. FIXING WRONG COMMIT

If commit message wrong:

```
git commit --amend
```

If you forgot file:

```
git add file
git commit --amend
```

Amend modifies last commit safely (if not pushed).

---

# 5. INTERACTIVE REBASE — HISTORY EDITOR

Edit past commits:

```
git rebase -i HEAD~3
```

You can:

* reorder commits
* edit commits
* squash commits
* delete commits

This is like:

> rewriting project history.

---

# 6. SQUASHING COMMITS — CLEAN HISTORY

Turn many commits into one:

```
git rebase -i HEAD~5
```

Change:

```
pick → squash
```

Used before merging feature branch.

Clean history = professional codebase.

---

# 7. SPLITTING A COMMIT

If commit too large:

```
git reset HEAD~1
```

Now changes unstaged.

Add selectively:

```
git add part1
git commit
git add part2
git commit
```

Experts maintain atomic commits.

---

# 8. STASH MASTER LEVEL

View stashes:

```
git stash list
```

Apply specific stash:

```
git stash apply stash@{2}
```

Delete stash:

```
git stash drop stash@{1}
```

Clear all:

```
git stash clear
```

---

# 9. CHERRY-PICK — SURGICAL COPY

Copy specific commit:

```
git cherry-pick commitID
```

Used when:

> you want one change but not whole branch.

---

# 10. REVERTING SAFELY IN TEAMS

Never reset public history.

Instead use:

```
git revert commitID
```

This creates new commit that cancels old one.

Safe for shared branches.

---

# 11. DIAGNOSING MERGE DISASTERS

If merge goes wrong:

Abort merge:

```
git merge --abort
```

Abort rebase:

```
git rebase --abort
```

Continue after fixing:

```
git rebase --continue
```

---

# 12. SEEING TRUE PROJECT GRAPH

View full commit graph:

```
git log --graph --oneline --all
```

This reveals actual history structure.

Experts visualize graph mentally.

---

# 13. DETECTING WHO INTRODUCED BUG

Find author of line:

```
git blame file.js
```

Find commit that added bug:

```
git log -p file.js
```

Used in debugging regressions.

---

# 14. BISECT — AUTOMATED BUG FINDER

Find which commit introduced bug:

```
git bisect start
git bisect bad
git bisect good commitID
```

Git performs binary search across commits.

This can locate bug in seconds even across thousands of commits.

---

# 15. FIXING DETACHED HEAD WORK

If you made commits in detached HEAD:

Save them:

```
git branch save-work
```

Now commits safe.

---

# 16. CLEANING WORKSPACE

Remove untracked files:

```
git clean -f
```

Preview:

```
git clean -n
```

---

# 17. RESET LEVELS — DEEP UNDERSTANDING

Soft reset:

```
git reset --soft HEAD~1
```

Keeps changes staged.

Mixed reset:

```
git reset HEAD~1
```

Keeps changes unstaged.

Hard reset:

```
git reset --hard HEAD~1
```

Deletes changes completely.

Experts choose carefully.

---

# 18. FORCE PUSH — DANGEROUS POWER

Overwrite remote history:

```
git push --force
```

Safe version:

```
git push --force-with-lease
```

Rule:

> Only experts use force push.

---

# 19. WHEN REPOSITORY BREAKS

Repair corrupted repo:

```
git fsck
```

Check object database integrity.

---

# 20. ADVANCED ENGINEER MINDSET

When Git issue happens:

Beginners panic.
Advanced engineers ask:

```
What pointer moved?
What commit exists?
Where is HEAD?
What graph changed?
```

They reason using Git’s internal model.

---

# ADVANCED LEVEL COMPLETION CHECK

You can now:

✔ recover lost commits
✔ edit history safely
✔ repair broken merges
✔ clean commit history
✔ debug repository problems
✔ locate bug-introducing commits
✔ manage complex histories

---

# FINAL LINE FOR THIS CHAPTER

At this point:

> Git no longer surprises you.

Because you now understand how to repair any mistake.

That is real mastery.

---

END OF LEVEL 5 — ADVANCED

---

📍 Next stage unlock:

Say
**PRO**

Next chapter:

> “You think like a version control architect and design workflows for teams.”
