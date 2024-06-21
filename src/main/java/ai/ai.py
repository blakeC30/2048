from langchain_community.chat_models import ChatOllama
from langchain_core.output_parsers import StrOutputParser
llm = ChatOllama(model="llama3")

from langchain_core.messages import HumanMessage
from langchain_core.messages import SystemMessage
import sys
messages = [
    SystemMessage(
        content = "You are an expert in the game 2048. You will be presented with the state of the board in the form of a matrix. Empty cells on the board are represented by -1. Your goal is to analyze the board and determine the next best move. You will answer each prompt with only UP, DOWN, LEFT, or RIGHT, depending on your determination of the next move. Your goal is to get the highest score. Score is increased when two cells combine. Try to keep similar numbers adjacent to each other."
    ),
    HumanMessage(
        content= sys.argv[1] + ". Remember to only answer with the direction of your move. Make sure to move in a direction that will cause cells to move."
    )
]
chain = llm | StrOutputParser()
chat_model_response = chain.invoke(messages)
print(chat_model_response)