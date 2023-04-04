$folderPath = "C:\ToDo\Installation"
$files = Get-ChildItem -Path $folderPath | Where-Object {$_.Extension -eq ".exe"}

foreach ($file in $files) {
    Write-Host "Installing $($file.Name)"
    Start-Process -FilePath $file.FullName -ArgumentList "/S" -Wait
    Write-Host "Installation of $($file.Name) completed"
}