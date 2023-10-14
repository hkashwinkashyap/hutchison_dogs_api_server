const baseUrl = 'http://ec2-51-20-65-141.eu-north-1.compute.amazonaws.com:8000/dogsApi';

function postRequestJson(requestData) {
    return {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData), // Convert the data to JSON string
    };
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function setColCount() {
    // Set colCount based on the window width
    if (window.innerWidth >= 990) { // Desktop size or larger
        return 4;
    } else if (window.innerWidth >= 720 && window.innerWidth < 990) {
        return 3;
    }
    else {
        return 2;
    }
}

function downloadHandle() {
    fetch(baseUrl + '/getAllDogs')
        .then((response) => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then((jsonData) => {
            let lowercaseData = {};
            for (const breed in jsonData.data) {
                const lowerBreed = breed.toLowerCase();
                const lowerSubBreeds = jsonData.data[breed].map((subBreed) => subBreed.toLowerCase());
                lowercaseData[lowerBreed] = lowerSubBreeds.sort();
            }

            const sortedKeys = Object.keys(lowercaseData).sort();

            const sortedLowercaseData = {};
            for (const key of sortedKeys) {
                sortedLowercaseData[key] = lowercaseData[key];
            }

            const jsonContent = JSON.stringify(sortedLowercaseData, null, 2)
            const blob = new Blob([jsonContent], { type: 'application/json' });
            const url = URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'dogs.json';
            a.style.display = 'none';
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
            URL.revokeObjectURL(url);
        })
        .catch((error) => {
            console.error('Error fetching data: ' + error);
        });
}

function addHandle() {
    const addBreedName = document.getElementById('dog-breed-add').value.trim().toLowerCase().replace(/\s+/g, " ");
    const addBreedTypeName = document.getElementById('dog-breedType-add').value.trim().toLowerCase().replace(/\s+/g, " ");

    if (addBreedName === '') {
        alert('Please enter a Breed name');
        return;
    }

    if (addBreedName !== '' && addBreedTypeName === '') {
        addDogBreed(addBreedName);
        $('#addModal').modal('hide'); // Close the modal
    }

    if (addBreedName !== '' && addBreedTypeName !== '') {
        addDogBreedType(addBreedName, addBreedTypeName);
        $('#addModal').modal('hide'); // Close the modal
    }
}

function addDogBreed(addBreedName) {

    fetch(baseUrl + '/addDogBreed', postRequestJson({ breedName: addBreedName }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function addDogBreedType(addTypeBreedName, addTypeBreedTypeName) {
    fetch(baseUrl + '/addDogBreedType', postRequestJson({
        breedName: addTypeBreedName,
        breedTypeName: addTypeBreedTypeName
    }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function removeHandle(removeBreedName, removeBreedTypeName) {

    if (removeBreedName === '') {
        alert('Please enter a Breed name');
        return;
    }

    if (removeBreedName !== '' && removeBreedTypeName === '') {
        removeDogBreed(removeBreedName);
    }

    if (removeBreedName !== '' && removeBreedTypeName !== '') {
        removeDogBreedType(removeBreedName, removeBreedTypeName);
    }
}

function removeDogBreed(removeBreedName) {
    message = 'Are you sure you want to remove "' + removeBreedName + '" Breed?';
    if (!confirm(message)) {
        return;
    }
    fetch(baseUrl + '/deleteDogBreed', postRequestJson({ breedName: removeBreedName }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function removeDogBreedType(removeTypeBreedName, removeTypeBreedTypeName) {
    message = 'Are you sure you want to remove "' + removeTypeBreedTypeName + '" from "' + removeTypeBreedName + '" Breed?';
    if (!confirm(message)) {
        return;
    }
    fetch(baseUrl + '/deleteDogBreedType', postRequestJson({
        breedName: removeTypeBreedName,
        breedTypeName: removeTypeBreedTypeName
    }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function updateHandle(updateBreedName, newBreedName, updateBreedTypeName, newBreedTypeName) {
    updateBreedName = updateBreedName.trim().toLowerCase().replace(/\s+/g, " ");
    newBreedName = newBreedName.trim().toLowerCase().replace(/\s+/g, " ");
    updateBreedTypeName = updateBreedTypeName.trim().toLowerCase().replace(/\s+/g, " ");
    newBreedTypeName = newBreedTypeName.trim().toLowerCase().replace(/\s+/g, " ");

    if (updateBreedName === '') {
        alert('Please enter a Breed name');
        return;
    }

    else if (updateBreedName !== '' && newBreedName !== '' && updateBreedTypeName === '' && newBreedTypeName === '') {
        updateBreedNameFunc(updateBreedName, newBreedName);
    }

    else if (updateBreedName !== '' && newBreedName === '' && updateBreedTypeName !== '' && newBreedTypeName !== '') {
        updateBreedTypeNameFunc(updateBreedName, updateBreedTypeName, newBreedTypeName);
    }

    else if (updateBreedName !== '' && newBreedName !== '' && updateBreedTypeName !== '' && newBreedTypeName !== '') {
        alert('All fields are filled, please enter a Breed name to update or a Breed Type name to update not both');
        return;
    }

    else {
        alert('Invalid input. No update done');
        return;
    }

}

function updateBreedNameFunc(updateBreedName, newBreedName) {
    fetch(baseUrl + '/updateDogBreedName', postRequestJson({
        breedName: updateBreedName,
        newBreedName: newBreedName
    }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function updateBreedTypeNameFunc(updateBreedName, updateBreedTypeName, newBreedTypeName) {
    fetch(baseUrl + '/updateDogBreedTypeName', postRequestJson({
        breedName: updateBreedName,
        breedTypeName: updateBreedTypeName,
        newBreedTypeName: newBreedTypeName
    }))
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response JSON
        })
        .then(data => {
            alert(data.message)
            loadHomePage();
        })
        .catch(error => {
            alert('Error: ' + error)
        });
}

function loadHomePage() {
    const dogList = document.getElementById('dog-list');
    const updateIcon = document.createElement("i");
    updateIcon.classList.add("bi", "bi-pencil");

    const deleteIcon = document.createElement("i");
    deleteIcon.classList.add("bi", "bi-trash3");

    const addIcon = document.createElement("i");
    addIcon.classList.add("bi", "bi-plus");

    dogList.innerHTML = '';


    fetch(baseUrl + '/getAllDogs')
        .then(response => response.json())
        .then(jsonData => {
            const lowercaseData = {};
            for (const breed in jsonData.data) {
                lowercaseData[breed.toLowerCase()] = jsonData.data[breed].map(subBreed => subBreed.toLowerCase());
            }
            const dogData = Object.keys(lowercaseData).sort();
            const dogList = document.getElementById('dog-list');
            let row = document.createElement("div");
            row.classList.add("row");
            dogList.appendChild(row);

            let colCount = 0; // Keep track of the number of columns

            for (const breed of dogData) {
                if (colCount === setColCount()) {
                    row = document.createElement("div");
                    row.classList.add("row");
                    dogList.appendChild(row);
                    colCount = 0;
                }

                const breedCard = document.createElement("div");
                breedCard.classList.add("card", "col");
                row.appendChild(breedCard);

                const breedCardContent = document.createElement("div");
                breedCardContent.classList.add("card-body");
                breedCard.appendChild(breedCardContent);

                const breedTitle = document.createElement("h5");
                breedTitle.classList.add("card-title");
                if (breed.length > 15) {
                    breedTitle.textContent = capitalizeFirstLetter(breed.substring(0, 15)) + '...';
                } else {
                    breedTitle.textContent = capitalizeFirstLetter(breed);
                }
                const addBreedTypeButton = document.createElement("button");
                addBreedTypeButton.classList.add("btn", "btn-outline-info", "btn-sm", "card-title-button");
                addBreedTypeButton.innerHTML = addIcon.outerHTML;
                addBreedTypeButton.onclick = function () {
                    const newBreedTypeNameEntered = prompt("Enter the new breed type for " + breed).trim().toLowerCase().replace(/\s+/g, " ");
                    if (newBreedTypeNameEntered === '') {
                        alert('No update done');
                        return;
                    }
                    addDogBreedType(breed, newBreedTypeNameEntered);
                };

                const deleteBreedButton = document.createElement("button");
                deleteBreedButton.classList.add("btn", "btn-outline-danger", "btn-sm", "card-title-button");
                deleteBreedButton.innerHTML = deleteIcon.outerHTML;
                deleteBreedButton.onclick = function () {
                    removeHandle(breed, '');
                };

                const updateBreedButton = document.createElement("button");
                updateBreedButton.classList.add("btn", "btn-outline-warning", "btn-sm", "card-title-button");
                updateBreedButton.innerHTML = updateIcon.outerHTML;
                updateBreedButton.onclick = function () {
                    const newBreedNameEntered = prompt('Update ' + breed + ' with:');
                    if (newBreedNameEntered === '') {
                        alert('Invalid input. No update done');
                        return;
                    }
                    updateHandle(breed, newBreedNameEntered, '', '');
                };

                const buttonsBreedSpan = document.createElement("span");
                buttonsBreedSpan.appendChild(addBreedTypeButton);
                buttonsBreedSpan.appendChild(document.createTextNode("   "));
                buttonsBreedSpan.appendChild(updateBreedButton);
                buttonsBreedSpan.appendChild(document.createTextNode("   "));
                buttonsBreedSpan.appendChild(deleteBreedButton);

                const breedContainer = document.createElement("div");
                breedContainer.classList.add("d-flex", "justify-content-between");
                breedContainer.appendChild(breedTitle);
                breedContainer.appendChild(buttonsBreedSpan);
                breedCardContent.appendChild(breedContainer);

                const breedTypeName = document.createElement("div");
                breedTypeName.classList.add("list-group");

                const breedTypeNames = lowercaseData[breed].sort();
                for (const subBreed of breedTypeNames) {
                    const breedTypeItem = document.createElement("li");
                    breedTypeItem.classList.add("list-group-item");

                    const deleteButton = document.createElement("button");
                    deleteButton.classList.add("btn", "btn-outline-danger", "btn-sm", "card-type-button");
                    deleteButton.innerHTML = deleteIcon.outerHTML;
                    deleteButton.onclick = function () {
                        removeHandle(breed, subBreed);
                    };

                    const updateButton = document.createElement("button");
                    updateButton.classList.add("btn", "btn-outline-warning", "btn-sm", "card-type-button");
                    updateButton.innerHTML = updateIcon.outerHTML;
                    updateButton.onclick = function () {
                        const newBreedTypeNameEntered = prompt('Update ' + subBreed + ' to:');
                        if (newBreedTypeNameEntered === '') {
                            alert('Invalid input. No update done');
                            return;
                        }
                        updateHandle(breed, '', subBreed, newBreedTypeNameEntered);
                    };

                    const buttonsSpan = document.createElement("span");
                    buttonsSpan.appendChild(updateButton);
                    buttonsSpan.appendChild(document.createTextNode("   "));
                    buttonsSpan.appendChild(deleteButton);

                    const typeContainer = document.createElement("div");
                    typeContainer.classList.add("d-flex", "justify-content-between");
                    if (subBreed.length > 15) {
                        typeContainer.appendChild(document.createTextNode(capitalizeFirstLetter(subBreed.substring(0, 15)) + '...'));
                    } else {
                        typeContainer.appendChild(document.createTextNode(capitalizeFirstLetter(subBreed)));
                    }
                    typeContainer.appendChild(buttonsSpan);

                    breedTypeItem.appendChild(typeContainer);
                    breedTypeName.appendChild(breedTypeItem);
                }

                breedCardContent.appendChild(breedTypeName);

                colCount++;
            }

        })
        .catch(error => {
            console.error('Error fetching data: ' + error);
        });
}

window.onload = loadHomePage;
window.onresize = loadHomePage;