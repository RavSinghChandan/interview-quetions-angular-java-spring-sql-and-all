# GIT — LEVEL 2 (BASIC)

**Stage Title: “You Take Control of Time”**

*(Story continues — now you don’t just understand Git… you start using it.)*

---

## CHAPTER CONTEXT — WHAT CHANGES HERE

At Naive level you learned:

> how Git thinks.

At Basic level you learn:

> how to speak Git’s language.

This is where you start controlling history.

Not theory.
Real actions.

---

# 1. YOUR FIRST REPOSITORY — CREATING HISTORY

Create folder:

```
mkdir project
cd project
```

Initialize Git:

```
git init
```

What just happened?

Git created hidden database:

```
.git/
```

Your folder is now a **repository**.

Meaning:

> Git is now watching this project.

---

# 2. GIT STATUS — YOUR MOST IMPORTANT COMMAND

Check state:

```
git status
```

This tells:

* which files changed
* which files staged
* which files not tracked

Rule:

> Always run `git status` before doing anything.

---

# 3. ADDING FILES TO HISTORY

Create file:

```
touch app.js
```

Git sees it as **untracked**.

Add it to staging:

```
git add app.js
```

Add all files:

```
git add .
```

Meaning:

> Prepare these changes for snapshot.

---

# 4. COMMIT — FREEZING TIME

Save snapshot:

```
git commit -m "initial commit"
```

Now Git permanently recorded this state.

Commit message should describe:

> what changed and why.

---

# 5. THE THREE STATES MODEL (CRITICAL)

Every file lives in one of these:

```
Working → edited
Staged → ready
Committed → saved
```

Flow:

```
edit → add → commit
```

This is Git’s core lifecycle.

---

# 6. VIEWING HISTORY

Show commits:

```
git log
```

Short view:

```
git log --oneline
```

This shows your project’s timeline.

---

# 7. MAKING CHANGES

Edit file:

```
echo "hello" >> app.js
```

Check status:

```
git status
```

Add change:

```
git add app.js
```

Commit:

```
git commit -m "added greeting"
```

You just created another snapshot in history.

---

# 8. IGNORING FILES (IMPORTANT)

Some files shouldn’t be tracked:

* logs
* node_modules
* build files
* secrets

Create ignore file:

```
touch .gitignore
```

Example content:

```
node_modules/
.env
*.log
```

Git will now ignore those.

---

# 9. SEEING CHANGES

View difference:

```
git diff
```

Shows what changed since last commit.

Compare staged changes:

```
git diff --staged
```

This is how you inspect before committing.

---

# 10. BRANCHES — YOUR FIRST PARALLEL WORLD

Create branch:

```
git branch feature
```

Switch branch:

```
git checkout feature
```

Or modern way:

```
git switch feature
```

Now you’re in separate timeline.

Changes here don’t affect main branch.

---

# 11. MERGING — COMBINING TIMELINES

Go back to main:

```
git checkout main
```

Merge branch:

```
git merge feature
```

Git combines histories.

---

# 12. DELETING BRANCH

After merge:

```
git branch -d feature
```

Keeps history clean.

---

# 13. REMOTE REPOSITORIES — SHARING HISTORY

Connect remote:

```
git remote add origin URL
```

Push code:

```
git push origin main
```

Download updates:

```
git pull
```

Clone project:

```
git clone URL
```

---

# 14. UNDOING MISTAKES (SAFE POWER)

Unstage file:

```
git reset file.txt
```

Discard changes:

```
git checkout -- file.txt
```

Undo last commit:

```
git reset --soft HEAD~1
```

---

# 15. RENAMING / DELETING FILES

Rename:

```
git mv old.txt new.txt
```

Delete:

```
git rm file.txt
```

---

# 16. WHO CHANGED WHAT

Show author of each line:

```
git blame file.js
```

Used in debugging.

---

# 17. BASIC WORKFLOW (REAL WORLD)

Daily developer flow:

```
git pull
edit files
git add .
git commit -m "message"
git push
```

That is real-world usage cycle.

---

# BASIC LEVEL COMPLETION CHECK

You can now:

✔ create repository
✔ stage files
✔ commit changes
✔ read history
✔ create branches
✔ merge branches
✔ connect remote
✔ push and pull
✔ undo mistakes

---

# FINAL LINE FOR THIS LEVEL

At this point in the story:

> You are no longer watching Git track history.

You are now controlling history yourself.

---

END OF LEVEL 2 — BASIC

---

📍 Next chapter unlock:

Say
**INTERMEDIATE**

Next stage:

> “You learn how teams safely work together using Git without breaking each other’s code.”
