---
name: Release
about: Use this template for creating a new release.
title: "Release: x.y.z"
labels: release
assignees: yannickkirschen
---

# Prerequisites

- [ ] All changes for the release have been merged (rebased) into `main`.
- [ ] All workflows succeeded.

# Steps

## Release

- [ ] Checkout branch `main` and pull latest changes:
    - `git checkout main`
    - `git pull origin main`
- [ ] Set verion in `pom.xml` to `x.y.z`.
- [ ] Rename "Next" section in `CHANGELOG.md` to `x.y.z (Month Day, Year)`.
- [ ] Commit and push your changes:
    - `git add .`
    - `git commit -s -m "build(release): x.y.z"`
    - `git push origin main`
- [ ] Check if all workflows succeeed.

## Prepare next release

- [ ] Checkout branch `main` and pull latest changes:
    - `git checkout main`
    - `git pull origin main`
- [ ] Set version in `pom.xml` to `x.y+1.z-SNAPSHOT`.
- [ ] Commit and push your changes:
    - `git add .`
    - `git commit -s -m "build(release): next development release x.y+1.z"`
    - `git push origin main`
- [ ] Check if all workflows succeeed.

