export const categorizeIngredint = (ingredients) => {
    return ingredients.reduce((acc, curr) => {
        const{category} = curr;
        if(!acc[category.name]){
            acc[category.name] = [];
        }
        acc[category.name].push(curr);
        return acc;
    } ,{})
}