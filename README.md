# Java Applications
To download only one folder(project) with GIT:
mkdir test1 & cd test1 & git init & git remote add origin https://github.com/dimeddy46/CppApps.git & git config core.sparseCheckout true & echo [changeThisWithFolderName]/* > .git/info/sparse-checkout & git fetch & git checkout master 

More projects:
echo [AnotherFolderName]/* >> .git/info/sparse-checkout & git checkout master

Clear:
git config core.sparseCheckout false & rd /s test1
