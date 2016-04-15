/*
 ============================================================================
 Name        : binary.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

struct node
{
    int value;
    struct node *right;
    struct node *left;
}node;

struct node *root=NULL, *temp = NULL, *LLhead= NULL;
int i = 0, count=0, q[50], front = 0, rear = 0;


//--------------------------------------------- Create BST //---------------------------------------------

void bst_create()
{

    struct node *n1;
    int val=0;
    count++;
    printf("Enter value : ");
    scanf("%d",&val);
    printf("\n");
    temp = (struct node *)malloc(sizeof(struct node));
    temp->value = val;
    temp->left = NULL;
    temp->right = NULL;


    if(root == NULL)
    {
        root = temp;
        q[rear] = root->value; rear++;
    }
    else
    {
        n1 = root;
        while(1)
        {
            if(temp->value >= n1->value && n1->right!= NULL)
            {
                n1 = n1->right;
            }
            else if(temp->value >= n1->value && n1->right == NULL)
            {
                n1->right = temp; break;
            }
            else if(temp->value < n1->value  && n1->left!= NULL)
            {
                n1 = n1->left;
            }
            else if(temp->value < n1->value && n1->left == NULL)
            {
                n1->left = temp; break;
            }
        }
        
    }
}

//--------------------------------------------- Height of BST //---------------------------------------------

int bst_ht(struct node *ht_root)
{
    int left_ht = 0, right_ht=0;
    if(ht_root == NULL)
        return -1;

    left_ht = bst_ht(ht_root->left);
    right_ht = bst_ht(ht_root->right);

    if(right_ht > left_ht)
        return right_ht+1;
    else
        return left_ht+1;
}

//--------------------------------------------- Display //---------------------------------------------


void bst_display(struct node *disp_root)
{
    int j=0;
    if(root == NULL)
    {
        printf("BST has no elements\n");
        return;
    }

    if(front<=rear && disp_root->value == q[front])
    {
        if(disp_root->left!=NULL)
            {q[rear]= disp_root->left->value; rear++;}
        if(disp_root->right!=NULL)
            {q[rear]= disp_root->right->value; rear++;}

        front++;
    }

    if(disp_root->left!=NULL)
    {
        bst_display(disp_root->left);
    }
    if(disp_root->right!=NULL)
    {
        bst_display(disp_root->right);
    }

}
void display(struct node *d,int count)
{
    int j=0, new = 1,line=0;
    bst_display(root);
    for(j = 0; j < count;j++)
    {
        printf("%d  ",q[j]);
    }
    printf("\n");
}
//--------------------------------------------- post order //---------------------------------------------

void bst_postorder(struct node *post_root)
{
    if(root == NULL)
    {
        printf("No nodes in BST");
        return;
    }
    if(post_root->left!=NULL)    
        bst_postorder(post_root->left);
    if(post_root->right!=NULL)    
    bst_postorder(post_root->right);
    printf("%d ",post_root->value);

}

//--------------------------------------------- in order //---------------------------------------------
void bst_inorder(struct node *in_root)
{
    if(root == NULL)
    {
        printf("No nodes in BST");
        return;
    }
    if(in_root->left!=NULL)    
        bst_inorder(in_root->left);
    printf("%d ",in_root->value);
    if(in_root->right!=NULL)    
        bst_inorder(in_root->right);

}

//--------------------------- BST to linked List ocnversion ------------------------------

void BST_to_LL(struct node *LLroot, struct node **pLastNodeInList)
{
    if(LLroot == NULL)
        return;

    struct node *pCurrent = LLroot;

    if (pCurrent->left != NULL)
        BST_to_LL(pCurrent->left, pLastNodeInList);

    pCurrent->left = *pLastNodeInList;
    if(*pLastNodeInList != NULL)
        (*pLastNodeInList)->right = pCurrent;

    *pLastNodeInList = pCurrent;

    if (pCurrent->right != NULL)
        BST_to_LL(pCurrent->right, pLastNodeInList);
}
void LL_display(struct node *LLhead)
{

    printf("BST to Double Linked List : \nnull <-->");
    while(LLhead !=NULL)
    {
        printf("[%d]",LLhead->value);
        if(LLhead->right !=NULL)
            printf(" <--> ");
        LLhead = LLhead->right;
    }
    printf(" <--> null\n\n");
    
}

//----------------------------------------------------------------------------------------------
//---------------------------------------------main---------------------------------------------
//----------------------------------------------------------------------------------------------

int main() {

    char n = 'y';
    int option, ht = 0;
    struct node *pLastNodeInList = NULL;

        while(1)
        {
        printf("# # OPERATIONS / Functions for Binary search tree :-\n");
        printf("-----------------------------------");
            printf("\n1 - Insert an element into BST\n");
            printf("2 - Inorder Display BST\n");
            printf("3 - Height of the BST\n");
            printf("4 - BST to Double LL conversion\n");
        printf("-----------------------------------\n");
        printf("5 - Exit\n");
        printf("-----------------------------------\n");
        printf("\nEnter your choice :- ");
        scanf("%d", &option);

            switch (option)
            {
            case 1:    
                bst_create();
                break;

            case 2:
        printf("INORDER BST is : - ");
        bst_inorder(root);
        printf("\n\n");
                break;

            case 3:    
                ht = bst_ht(root);
        printf("Height of the given BST is : %d\n\n",ht);
        break;

            case 4:
        BST_to_LL(root, &pLastNodeInList);
        struct node *pHeadOfList = pLastNodeInList;
        while(pHeadOfList != NULL && pHeadOfList->left != NULL)
                pHeadOfList = pHeadOfList->left;
        LLhead = pLastNodeInList;
        LL_display(pHeadOfList);
        break;


        case 5:
                exit(0);
            default :     
                printf("Wrong choice, Please enter correct choice  ");
                break;    
            }
        }

    return EXIT_SUCCESS;
}
